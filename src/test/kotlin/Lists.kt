import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lists : StringSpec({
    "basic immutable list creation" {
        val cars = listOf("BMW", "Mercedes", "Toyota")
        cars.joinToString(",") shouldBe "BMW,Mercedes,Toyota"
    }

    "you can create an empty list" {
        val empty = emptyList<String>()
        empty.size shouldBe 0
    }

    "you can create a list using a builder" {
        val names = listOf<String>("Emma", "Kim")

        val list = buildList {
            add("Marta")
            addAll(names)
            add("Kira")
        }

        list.joinToString(",") shouldBe "Marta,Emma,Kim,Kira"
    }

    "indexOf(element) returns the index of the first occurrence of the specified element" {
        val names = listOf("Emma", "Kim", "Marta", "Kira")

        names.indexOf("Kim") shouldBe 1
        names.indexOf("Mònica") shouldBe -1
    }

    "contains(element) returns true if the element specified is in the list" {
        val names = listOf("Emma", "Kim", "Marta", "Kira")
        names.contains("Kim") shouldBe true
        names.contains("Monica") shouldBe false
    }

    "you can iterate using for" {
        val names = listOf("Emma", "Kim", "Marta", "Kira")
        for((index, name) in names.withIndex()) {
            when(index) {
                0 -> name shouldBe "Emma"
                1 -> name shouldBe "Kim"
                2 -> name shouldBe "Marta"
                3 -> name shouldBe "Kira"
                else -> name shouldBe "NoName"
            }
        }
    }
})