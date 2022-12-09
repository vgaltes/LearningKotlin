import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Maps : StringSpec({
    "a map holds pair of objects" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        students.toString() shouldBe "{Manolo=5, Laura=4, Pepa=5}"
    }

    "we can access the value by its key" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        students["Laura"] shouldBe 4
    }

    "each entry is a Pair, which we can construct with 'to' and also deconstruct" {
        var (key, value) = "Martí" to 4
        key shouldBe "Martí"
        value shouldBe 4

        var (k, v) = Pair("Martí", 4)
        k shouldBe "Martí"
        v shouldBe 4
    }

    "we can create an empty map" {
        val emptyMap = emptyMap<String, Int>()
        emptyMap.size shouldBe 0
        emptyMap.isEmpty() shouldBe true
    }

    "we can use a builder to create a map" {
        val values = mapOf<String, Int>("Second" to 2, "Third" to 3)
        val map = buildMap<String, Int> {
            put("First", 1)
            putAll(values)
            put("Fourth", 4)
        }

        map.toString() shouldBe "{First=1, Second=2, Third=3, Fourth=4}"
    }

    "we can check if a key is present" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        students.containsKey("Manolo") shouldBe true
    }

    "we can check if a value is present" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        students.containsValue(5) shouldBe true
    }

    "we can iterate through the elements as entries" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        var index = 0
        for(student in students){
            if ( index == 0) {
                student.key shouldBe "Manolo"
                student.value shouldBe 5
            }
            if ( index == 1) {
                student.key shouldBe "Laura"
                student.value shouldBe 4
            }
            if ( index == 2) {
                student.key shouldBe "Pepa"
                student.value shouldBe 5
            }
            index++
        }
    }

    "we can iterate through the elements by decomposing them" {
        val students = mapOf(
            "Manolo" to 5,
            "Laura" to 4,
            "Pepa" to 5
        )

        var index = 0
        for((key, value) in students){
            if ( index == 0) {
                key shouldBe "Manolo"
                value shouldBe 5
            }
            if ( index == 1) {
                key shouldBe "Laura"
                value shouldBe 4
            }
            if ( index == 2) {
                key shouldBe "Pepa"
                value shouldBe 5
            }
            index++
        }
    }

    "you can add elements to a mutable map" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff["Jane"] = 900
        staff.size shouldBe 4
    }

    "you can create a mutable map in a variety of ways" {
        var students = mutableMapOf("Nika" to 19, "Mike" to 23)
        students.size shouldBe 2

        var studentsMap = mapOf("Nika" to 19, "Mike" to 23)
        students = studentsMap.toMutableMap()
        students.size shouldBe 2
    }

    "you can add elements using put" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff.put("Jane", 900) // equivalent to assignment staff["Jane"] = 900
    }

    "you can add elements using putAll" {
        val staff = mapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        val allStaff = mutableMapOf("Jane" to 900)
        allStaff.putAll(staff)

        allStaff.size shouldBe 4
    }

    "you can add an item with the plus assign operator" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff += "Mika" to 900

        staff.size shouldBe 4
    }

    "when you try to associate a specified value in the map with a key that already exists, the existing value will be overwritten" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff["John"] = 600

        staff["John"] shouldBe 600
    }

    "you can prevent from modifying and existent item with putIfAbsent" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff.putIfAbsent("John", 700)

        staff["John"] shouldBe 500
    }

    "you can remove the specified key" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff.remove("John")

        staff.size shouldBe 2
    }

    "you can remove an item using the minus assign" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff -= "John"

        staff.size shouldBe 2
    }

    "you can clear the map" {
        val staff = mutableMapOf(
            "John" to 500,
            "Mike" to 1000,
            "Lara" to 1300
        )

        staff.clear()

        staff.size shouldBe 0
    }
})