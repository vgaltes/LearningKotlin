import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAllValues
import io.kotest.matchers.shouldBe

class Arrays : StringSpec({
    "you can create arrays of integers" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        numbers.joinToString() shouldBe "1, 2, 3, 4, 5"

    }

    "you can create arrays of characters" {
        val characters = charArrayOf('K', 't', 'l')
        characters.joinToString() shouldBe "K, t, l"
    }

    "you can create arrays of doubles" {
        val doubles = doubleArrayOf(1.25, 0.17, 0.4)
        doubles.joinToString() shouldBe "1.25, 0.17, 0.4"
    }

    "you can create an array of specified size" {
        val numbers = IntArray(5)
        numbers.joinToString() shouldBe "0, 0, 0, 0, 0"
    }

    "we can create an array from a string" {
        val input = "1 2 3 4 5"
        val numbers = input.split(" ").map { it.toInt() }.toTypedArray()
        numbers.joinToString() shouldBe "1, 2, 3, 4, 5"
    }

    "we can access the elements by their index" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        numbers[2] shouldBe 3

        var cart = mutableMapOf<String, Int>()
        var total = 0
        for ((k, v) in cart) total += v
    }

    "we get an exception if the index is invalid" {
        val exception = shouldThrow<ArrayIndexOutOfBoundsException> {
            val numbers = intArrayOf(1, 2, 3, 4, 5)
            numbers[20]
        }

        exception.message shouldBe "Index 20 out of bounds for length 5"
    }

    "we can access the first element using first" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        numbers.first() shouldBe 1
    }

    "we can access the last element in different ways" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)

        numbers.last() shouldBe 5
        numbers[numbers.size - 1] shouldBe 5
        numbers[numbers.lastIndex] shouldBe 5
    }

    "to compare the contents of the arrays we can use contentEquals" {
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        val moreNumbers = intArrayOf(1, 2, 3, 4, 5)
        val evenMoreNumbers = intArrayOf(1, 2, 3, 4)

        numbers.contentEquals(moreNumbers) shouldBe true
        numbers.contentEquals(evenMoreNumbers) shouldBe false
    }

    "arrays can be added" {
        val southernCross = arrayOf("Acrux", "Gacrux", "Imai", "Mimosa")
        val stars = arrayOf("Ginan", "Mu Crucis")

        val newArray = southernCross + stars

        newArray.joinToString() shouldBe("Acrux, Gacrux, Imai, Mimosa, Ginan, Mu Crucis")
    }
})