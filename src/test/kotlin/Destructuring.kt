import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.Exception

class Destructuring : StringSpec({
    "you can destructure a data class" {
        data class User(val name: String, val age: Int, val isAdmin: Boolean)

        val anonim = User("Anonim", 999, false)

        val (userName, userAge, isAdmin) = anonim

        userName shouldBe anonim.component1()
        userAge shouldBe anonim.component2()
        isAdmin shouldBe anonim.component3()
    }

    "if you don't have a data class, you must create the componentN operator manually" {
        class User(val name: String, val age: Int, val isAdmin: Boolean){
            operator fun component1(): String = name
            operator fun component2(): Int = age
            operator fun component3(): Boolean = isAdmin
        }

        val suspiciousUser = User("Anonim", 999, false)
        val (name, age, isAdmin) = suspiciousUser

        name shouldBe suspiciousUser.component1()
        age shouldBe suspiciousUser.component2()
        isAdmin shouldBe suspiciousUser.component3()
    }

    "destructuring works with lists" {
        val list = listOf(1,2,3)
        val (a, b, c) = list

        a shouldBe 1
        b shouldBe 2
        c shouldBe 3
    }

    "if the list has less elements than the variables to destructure, it will fail" {
        val exception = shouldThrow<Exception> {
            val list = listOf(1,2)
            val (a, b, c) = list
            c
        }

        exception.message shouldBe "Index 2 out of bounds for length 2"
    }

    "destructuring works with for loops" {
        data class User(val name: String, val age: Int, val isAdmin: Boolean)
        val list = listOf(
            User("user 1", 18, true),
            User("user 2", 19, false),
            User("user 3", 20, false),
            User("user 4", 21, false)
        )

        var index = 0

        for((name, age, isAdmin) in list) {
            name shouldBe list[index].name
            age shouldBe list[index].age
            isAdmin shouldBe list[index].isAdmin
            index++
        }
    }
    
    "you can use underscore for unused variables" {
        val list = listOf(1,2,3)
        val (a, _, _) = list

        a shouldBe 1
    }
    
    "you can use trailing coma for unused variables" {
        val list = listOf(1,2,3)
        val (a,  ) = list

        a shouldBe 1
    }
})