import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Set : StringSpec({
    "Set is an immutable unordered collection of elements that does not allow duplicates" {
        val visitors = setOf("Vlad", "Vanya", "Liza", "Liza")
        visitors.size shouldBe 3
        visitors.toString() shouldBe "[Vlad, Vanya, Liza]"
    }

    "we can create an empty set" {
        val visitors = emptySet<String>()
        visitors.size shouldBe 0
        visitors.isEmpty() shouldBe true
        visitors.toString() shouldBe "[]"
    }

    "we can create a builder to create a set" {
        val oldVisitors = listOf("Vlad", "Vanya")
        val visitors = buildSet<String> {
            add("Vlad")
            addAll(oldVisitors)
            add("Liza")
        }

        visitors.size shouldBe 3
        visitors.toString() shouldBe "[Vlad, Vanya, Liza]"
    }

    "you can access the first and last elements of a set" {
        val visitors = setOf("Vlad", "Vanya", "Liza", "Liza")

        visitors.first() shouldBe "Vlad"
        visitors.last() shouldBe "Liza"
    }

    "you can check that all elements from a specific collection are contained in a Set" {
        val studentsOfAGroup = setOf("Bob", "Larry", "Vlad")
        val studentsInClass = setOf("Vlad")

        studentsInClass.containsAll(studentsOfAGroup) shouldBe false
    }

    "you can add two sets together using the + operator" {
        val productsList1 = setOf("Banana", "Lime", "Strawberry")
        val productsList2 = setOf("Strawberry")

        val finalProductsList = productsList1 + productsList2

        finalProductsList.size shouldBe 3
        finalProductsList.toString() shouldBe "[Banana, Lime, Strawberry]"
    }

    "you can subtract one set from another using the - operator" {
        val productsList1 = setOf("Banana", "Lime", "Strawberry")
        val productsList2 = setOf("Strawberry")

        val finalProductsList = productsList1 - productsList2

        finalProductsList.size shouldBe 2
        finalProductsList.toString() shouldBe "[Banana, Lime]"
    }

    "you can convert a mutable list to a set" {
        val groceries = mutableListOf("Pen", "Pineapple", "Apple", "Super Pen", "Apple", "Pen")
        val set = groceries.toSet()
        set.size shouldBe 4
        set.toString() shouldBe "[Pen, Pineapple, Apple, Super Pen]"
    }

    "you can add elements to a mutable set" {
        val groceries = mutableSetOf("Pineapple", "Apple")
        groceries.add("Pear")

        groceries.toString() shouldBe "[Pineapple, Apple, Pear]"
    }

    "you can add elements using AddAll" {
        val words = mutableSetOf<String>("Apple", "Coke")
        val friendsWords = mutableSetOf<String>("Banana", "Coke")

        words.addAll(friendsWords)
        words.toString() shouldBe "[Apple, Coke, Banana]"
    }

    "you can remove elements using remove, removeAll and clear" {
        val groceries = mutableSetOf("Apple", "Water", "Banana", "Pen")

        groceries.remove("Apple")
        groceries.toString() shouldBe "[Water, Banana, Pen]"

        val uselessGroceries = setOf("Banana", "Pen")
        groceries.removeAll(uselessGroceries)
        groceries.toString() shouldBe "[Water]"

        groceries.clear()
        groceries.toString() shouldBe "[]"
    }
})