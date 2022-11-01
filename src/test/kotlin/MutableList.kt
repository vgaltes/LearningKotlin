import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class MutableList : StringSpec({
    "you can create one with mutableListOf" {
        val mutableList = mutableListOf(1,3,5,7,9)

        mutableList.count() shouldBe 5
        mutableList.size shouldBe 5
    }

    "you can access the elements by their index" {
        val mutableList = mutableListOf(1,3,5,7,9)

        mutableList[0] shouldBe 1
        mutableList[1] shouldBe 3
        mutableList[2] shouldBe 5
        mutableList[3] shouldBe 7
        mutableList[4] shouldBe 9
    }

    "you can creat an empty list" {
        val mutableList = mutableListOf<Int>()

        mutableList.isEmpty() shouldBe true
    }

    "you can create a mutableList of an specified size" {
        val mutableList = MutableList(5) { index ->  index * 2}

        mutableList shouldHaveSize 5
        mutableList.size shouldBe 5

        mutableList[0] shouldBe 0
        mutableList[1] shouldBe 2
        mutableList[2] shouldBe 4
        mutableList[3] shouldBe 6
        mutableList[4] shouldBe 8
    }

    "you can convert an array into a mutableList" {
        val array = arrayOf(1,3,5,7,9)

        val mutableList = array.toMutableList()

        mutableList[0] shouldBe 1
        mutableList[1] shouldBe 3
        mutableList[2] shouldBe 5
        mutableList[3] shouldBe 7
        mutableList[4] shouldBe 9
    }

    "you can change the values using the index" {
        val mutableList = mutableListOf(0,0,0,0,0)

        mutableList[0] = 1
        mutableList[1] = 3
        mutableList[2] = 5
        mutableList[3] = 7
        mutableList[4] = 9

        mutableList[0] shouldBe 1
        mutableList[1] shouldBe 3
        mutableList[2] shouldBe 5
        mutableList[3] shouldBe 7
        mutableList[4] shouldBe 9
    }

    "there are helpers to access the first and last value" {
        val mutableList = mutableListOf(1,3,5,7,9)

        mutableList.first() shouldBe 1
        mutableList.last() shouldBe 9
    }

    "joinToString converts the mutable list into a string with a separator" {
        val southernCross = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")

        val result = southernCross.joinToString(" -> ")

        result shouldBe "Acrux -> Gacrux -> Imai -> Mimosa"
    }

    "mutable lists can be joined" {
        val southernCross = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
        southernCross.size shouldBe 4

        val stars = mutableListOf("Ginan", "Mu Crucis")
        stars.size shouldBe 2

        val newList = southernCross + stars
        newList.size shouldBe 6
        newList[0] shouldBe "Acrux"
        newList[4] shouldBe "Ginan"
    }

    "mutable lists can be compared" {
        val firstList = mutableListOf("result", "is", "true")
        val secondList = mutableListOf("result", "is", "true")
        val thirdList = mutableListOf("result")

        (firstList == secondList) shouldBe true
        (firstList == thirdList) shouldBe false
        (secondList != thirdList) shouldBe true
    }

    "you can change lists" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
        val stars = mutableListOf("Ginan", "Mu Crucis")

        val removed = mutableList.removeAt(0)
        mutableList[0] shouldBe "Gacrux"
        removed shouldBe "Acrux"

        mutableList.remove("Mimosa")
        mutableList.size shouldBe 2

        mutableList.add("Ginan")
        mutableList.last() shouldBe "Ginan"

        mutableList.clear()
        mutableList.size shouldBe 0

        mutableList.addAll(stars)
        mutableList.size shouldBe 2

        mutableList.add(0, "Acrux")
        mutableList.size shouldBe 3
        mutableList.first() shouldBe "Acrux"

        mutableList += stars
        mutableList.size shouldBe 5
    }

    "you can copy list content" {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val copyList = list.toMutableList()

        (list == copyList) shouldBe true
    }

    "check for emptiness" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
        mutableList.isEmpty() shouldBe false
        mutableList.isNotEmpty() shouldBe true

        mutableList.clear()
        mutableList.isEmpty() shouldBe true
        mutableList.isNotEmpty() shouldBe false
    }

    "you can create a sublist" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
        val subList = mutableList.subList(1,3)

        subList.size shouldBe 2
        subList[0] shouldBe "Gacrux"
        subList[1] shouldBe "Imai"
    }

    "you can check if an element is in the list" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")

        ("Gacrux" in mutableList) shouldBe true
        ("Ginan" in mutableList) shouldBe false
    }

    "you can search for the index of an element" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")

        (mutableList.indexOf("Gacrux") shouldBe 1)
        (mutableList.indexOf("Ginan") shouldBe -1)
    }

    "you can search for min and max" {
        val mutableList = mutableListOf(4, 3, 5, 6, 1, 9, 0, 3)

        mutableList.min() shouldBe 0
        mutableList.max() shouldBe 9
    }

    "you can sum the elements" {
        val mutableList = mutableListOf(4, 3, 5, 6, 1, 9, 0, 3)

        mutableList.sum() shouldBe 31
    }

    "you can sort the elements" {
        val mutableList = mutableListOf(4, 3, 5, 6, 1, 9, 0, 3)

        val sorted = mutableList.sorted()
        sorted.first() shouldBe 0
        sorted.last() shouldBe 9

        val sortedDescending = mutableList.sortedDescending()
        sortedDescending.first() shouldBe 9
        sortedDescending.last() shouldBe 0
    }

    "you can transform a list into a mutableList" {
        val list = listOf(4, 3, 5, 6, 1, 9, 0, 3).toMutableList()
        list.add(10)

        list.size shouldBe 9
    }

    "you can replace an element in a certain position" {
        val mutableList = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
        mutableList.set(1, "Kandemor")

        mutableList[1] shouldBe "Kandemor"
    }
})