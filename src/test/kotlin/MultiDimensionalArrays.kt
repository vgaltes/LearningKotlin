import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MultiDimensionalArrays: StringSpec({
    "you can create a two dimensional array" {
        val array2D = arrayOf(
            arrayOf(0, 1, 2, 3),
            arrayOf(4, 5, 6, 7),
            arrayOf(8, 9, 10, 11)
        )

        array2D[0][0] shouldBe 0
        array2D[1][0] shouldBe 4
        array2D[1][3] shouldBe 7
        array2D[2][3] shouldBe 11
    }

    "you can create nested arrays with different numbers of elements in the same 2D array" {
        val array2D = arrayOf(
            arrayOf(0),
            arrayOf(1, 2),
            arrayOf(3, 4, 5))

        array2D[0][0] shouldBe 0
        array2D[1][1] shouldBe 2
        array2D[2][2] shouldBe 5
    }

    "nested arrays can be of different types" {
        val array2D = arrayOf(
            arrayOf("Practice", "makes", "perfect"),
            arrayOf(1, 2)
        )

        array2D[0][0] shouldBe "Practice"
        array2D[1][0] shouldBe 1
    }

    "you can get all the contents in a string" {
        val array2D = arrayOf(
            arrayOf("Practice", "makes", "perfect"),
            arrayOf(1, 2)
        )

        array2D.contentDeepToString() shouldBe "[[Practice, makes, perfect], [1, 2]]"
    }

    "we can create arrays of more dimensions" {
        val array3D = arrayOf(
            arrayOf(arrayOf(0,1), arrayOf(2,3)),
            arrayOf(arrayOf(4,5), arrayOf(6,7))
        )

        array3D.contentDeepToString() shouldBe "[[[0, 1], [2, 3]], [[4, 5], [6, 7]]]"
        array3D[1][0][1] shouldBe 5
        array3D[1][1][1] shouldBe 7
        array3D[0][0][1] shouldBe 1
    }
})