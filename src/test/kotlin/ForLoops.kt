import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ForLoops : StringSpec({
    "a for loop iterates through a range" {
        var index = 0

        for(i in 1..5) {
            i shouldBe ++index
        }
    }

    "you can iterate through a range of characters" {
        val expected = arrayOf('a', 'b', 'c')
        var index = 0

        for(ch in 'a'..'c') {
            ch shouldBe expected[index++]
        }
    }

    "you can iterate through a string" {
        val expected = arrayOf('h', 'e', 'l', 'l', 'o')
        var index = 0

        for(ch in "hello") {
            ch shouldBe expected[index++]
        }
    }

    "you can iterate in the backward order" {
        var index = 5

        for(i in 1 downTo 5) {
            i shouldBe index--
        }
    }

    "if you want to exclude the upper limit use until" {
        var index = 0
        var lastNumber = 0

        for(i in 1 until 5) {
            i shouldBe ++index
            lastNumber = i
        }

        lastNumber shouldBe 4
    }

    "you can specify a step" {
        var index = 0
        val expected = arrayOf(1,3,5,7)

        for(i in 1..7 step 2) {
            i shouldBe expected[index++]
        }
    }

    "factorial of a number" {
        val n = 5
        var result = 1

        for (i in 2..n) {
            result *= i
        }

        result shouldBe 120
    }
})