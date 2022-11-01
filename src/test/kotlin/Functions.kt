import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Functions : StringSpec({
    "we can get a reference to a function" {
        fun sum (a: Int, b:Int) : Int = a + b

        val sumObject = ::sum
        sumObject(1, 2) shouldBe 3

        val anotherSumObject: (Int, Int) -> Int = ::sum
        anotherSumObject(2, 3) shouldBe 5

    }

    "we can return a function" {
        fun getRealGrade(x: Double) = x
        fun getGradeWithPenalty(x: Double) = x - 1

        fun getScoringFunction(isCheater: Boolean): (Double) -> Double {
            if (isCheater) {
                return ::getGradeWithPenalty
            }

            return ::getRealGrade
        }

        getScoringFunction(true)(10.0) shouldBe 9
        getScoringFunction(false)(10.0) shouldBe 10
    }

    "we can pass a function as an argument" {
        fun applyAndSum(a: Int, b: Int, transformation: (Int) -> Int): Int {
            return transformation(a) + transformation(b)
        }

        fun same(x: Int) = x
        fun square(x: Int) = x * x
        fun triple(x: Int) = 3 * x

        applyAndSum(1, 2, ::same) shouldBe 3
        applyAndSum(1, 2, ::square) shouldBe 5
        applyAndSum(1, 2, ::triple) shouldBe 9
    }

    "you can reference functions that belong to a class" {
        class Person(val name: String, val lastname: String) {
            fun printFullName(): String {
                return("$name $lastname")
            }
        }

        val person: Person = Person("Sara", "Rogers")
        val personFun: () -> String = person::printFullName

        personFun() shouldBe "Sara Rogers"
    }

    "function references also work with functions from Kotlin standard classes" {
        val dec: (Int) -> Int = Int::dec

        dec(4) shouldBe 3
    }

    "we can reference a constructor" {
        class Person (val name: String)

        val personGenerator: (String) -> Person = ::Person

        val johnFoster: Person = personGenerator("John Foster")

        johnFoster.name shouldBe "John Foster"
    }
})