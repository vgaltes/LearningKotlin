import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MultiDimensionalLists : StringSpec({
    "you can easily create a multidimensional list by nesting mutableLists" {
        val multList = mutableListOf(
            mutableListOf(1,2,3),
            mutableListOf(4, 5, 6)
        )

        multList[0][0] shouldBe 1
        multList[1][2] shouldBe 6
    }

    "mutlidimensional lists can be of different size" {
        val multList = mutableListOf(
            mutableListOf(1,2,3),
            mutableListOf(4,5)
        )

        multList[0][0] shouldBe 1
        multList[1][1] shouldBe 5
    }

    "multidimensional lists can be of different types" {
        val multList = mutableListOf(
            mutableListOf(1,2,3),
            mutableListOf("A", "B", "C")
        )

        multList[0][0] shouldBe 1
        multList[1][2] shouldBe "C"
    }

    "you can print a line" {
        val multList = mutableListOf(
            mutableListOf('K','O','T', 'L', 'I', 'N'),
            mutableListOf("A", "B", "C")
        )

        multList[0].joinToString() shouldBe "K, O, T, L, I, N"
    }

    "you can print the whole list" {
        val mutListOfChar2D = mutableListOf(
            mutableListOf('k'),
            mutableListOf('o', 't'),
            mutableListOf('l', 'i', 'n'))

        mutListOfChar2D.toString() shouldBe "[[k], [o, t], [l, i, n]]"
    }

    "you can use more dimensions" {
        val mutList3D = mutableListOf(
            mutableListOf(mutableListOf(0,1), mutableListOf(2,3)),
            mutableListOf(mutableListOf(4,5), mutableListOf(6,7))
        )

        mutList3D[0][0][1] shouldBe 1
        mutList3D[1][0][1] shouldBe 5
        mutList3D[1][1][1] shouldBe 7
    }
})