import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class LambdaFunctions : StringSpec({
    "you can create a lambda function" {
        val mul = { a: Int, b: Int -> a * b }

        mul(2, 3) shouldBe 6
    }

    "if you create a lambda function without arguments, you can skip the arrow" {
        val three = { 3 }

        three() shouldBe 3
    }

    "you can pass a lambda to the filter function ( a function that expects another function)" {
        val originalText = "I don't know... what to say..."
        val textWithoutDots = originalText.filter({ c: Char -> c != '.' })

        textWithoutDots shouldBe "I don't know what to say"
    }

    "you can skip the type specification" {
        val originalText = "I don't know... what to say..."
        val textWithoutDots = originalText.filter({ c -> c != '.' })

        textWithoutDots shouldBe "I don't know what to say"
    }

    "if a lambda is the last argument, you can remove the parentheses" {
        val originalText = "I don't know... what to say..."
        val textWithoutDots = originalText.filter { c -> c != '.' }

        textWithoutDots shouldBe "I don't know what to say"
    }

    "if the lambda is longer than one line, the last line is treated as the return value" {
        val originalText = "1 2 3 4 5 6 7 8 9"

        val textWithoutSmallDigits = originalText.filter {
            val isNotDigit = !it.isDigit()
            val stringRepresentation = it.toString()

            isNotDigit || stringRepresentation.toInt() >= 5
        }.trimStart()

        textWithoutSmallDigits shouldBe "5 6 7 8 9"
    }

    "a lambda can have earlier returns. Usually, the name of the function where the lambda was passed is used" {
        val originalText = "1 2 3 4 5 6 7 8 9"
        val textWithoutSmallDigits = originalText.filter {
            if (!it.isDigit()) {
                return@filter true
            }

            it.toString().toInt() >= 5
        }.trimStart()

        textWithoutSmallDigits shouldBe "5 6 7 8 9"
    }

    "all the variables and values which are visible where the lambda is created are visible inside the lambda too -> the lambda captures the variable"  {
        var count = 0

        val increment = {
            ++count
        }

        count shouldBe 0
        increment()
        count shouldBe 1
        count += 10
        count shouldBe 11
    }

    "we can do partial invocations" {
        fun placeArgument(value: Int, f: (Int, Int) -> Int): (Int) -> Int {
            return { i -> f(value, i) }
        }

        val mul = { a: Int, b: Int -> a * b }

        val triple = placeArgument(3, mul)

        triple(4) shouldBe 12
    }

    "when splitting the lambda in more than one line, don't add extra brackets because that will mean that you're returning another lambda" {
        val lambda = { leftBorder: Long, rightBorder: Long ->
            if ( leftBorder == rightBorder) {
                leftBorder
            }
            else {
                var result = 1L
                for(num in leftBorder..rightBorder) result *= num
                result
            }
        }

        lambda(1L,1L) shouldBe 1L
        lambda(1L, 4L) shouldBe 24L
    }

    "you can define an extension function of a given type" {
        // here the receiver is an object instance that extends its functionality by the function
        fun Int.isEven() = this % 2 == 0

        3.isEven() shouldBe false
    }

    "you can define a lambda with a receiver" {
        // A lambda with a receiver is a way to define behavior similar to an extension function,
        // and it uses lambda expressions to operate with some object
        // Inside the body of the function literal, you can access the members of the receiver object using the expression this

        val sum: Int.(Int) -> Int = { a -> this + a }

        1.sum(2) shouldBe 3
    }

    "you can combine an extension function and a lambda with a receiver" {
        fun Int.opp(f: Int.() -> Int): Int = f()

        val res = 10.opp { this * 2 }

        res shouldBe 20
    }

    "you can create a type safe builder or a DSL with a lambda with a receiver" {
        fun myString(init: StringBuilder.() -> Unit): String {
            return StringBuilder().apply(init).toString()
        }

        val str = myString {
            append("Hello, ".uppercase())
            append("World!")
        }

        str shouldBe "HELLO, World!"
    }
})