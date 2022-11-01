import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

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
})