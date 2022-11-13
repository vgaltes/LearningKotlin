import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class A {
    fun member(): String = "hi from member"
}

val A.salutation: String
    get() { return "Hi!"}

class MyRepository<T> {
    companion object { }
}

class ExtensionFunctions : StringSpec({

    "you can create a basic extension function" {
        fun String.repeated(): String = this + this

        "ha".repeated() shouldBe "haha"
    }

    "the class to be extended is the receiver type and the object is the receiver object" {
        class Client(val name: String, val age: Int)

        fun Client.getInfo() = "$name $age" // Client is the receiver type


        val client = Client("John", 32)
        client.getInfo() shouldBe "John 32" // client is the receiver object
    }

    "member functions always win" {
        class A {
            fun member(): String = "hi from member"
        }

        fun A.member(): String = "hi from extension"

        val str = A().member()

        str shouldBe "hi from member"

    }

    "we can extend via a property as well" {
        val a = A()
        a.salutation shouldBe "Hi!"
    }

    "we can define extension methods inside a type and then it won't have global visibility" {
        open class StringFormatter {
            fun String.toUppercaseCustom():String = this.uppercase()
        }

        class SomeClass : StringFormatter() {
            fun getUpperCase(input: String) = input.toUppercaseCustom()
        }

        val c = SomeClass()
        c.getUpperCase("hello") shouldBe "HELLO"
    }

    "extension functions are statically resolved" {
        open class User
        class Customer : User()

        fun User.presentMyself(): String = "Hi, I'm a user"
        fun Customer.presentMyself(): String = "Hi, I'm a customer"

        val user: User = User()
        val customer: Customer = Customer()
        val hiddenCustomer: User = Customer()

        user.presentMyself() shouldBe "Hi, I'm a user"
        customer.presentMyself() shouldBe "Hi, I'm a customer"
        hiddenCustomer.presentMyself() shouldBe "Hi, I'm a user"
    }

    "can extend companion objects, nullable types, and generic types" {
        fun MyRepository.Companion.find(): String = "found"
        fun <T> MyRepository<T>?.findNullable(): String = "found"

        MyRepository.find() shouldBe "found"

        val r: MyRepository<String>? = null
        r?.findNullable() shouldBe null

        val r2: MyRepository<String>? = MyRepository<String>()
        r2.findNullable() shouldBe "found"
    }
})

