import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LazyInitialization : StringSpec({
    "you can lazy initialize a varible using the function lazy" {
        val messageList = mutableListOf<String>()
        val a: String by lazy {
            messageList.add("Initializing a")
            "a Initialized"
        }

        messageList.add("program started")
        val b = a

        messageList[0] shouldBe "program started"
        messageList[1] shouldBe "Initializing a"
        a shouldBe "a Initialized"
    }

    "lateinit allow us to set the value of the variable at a convenient point after the object is created" {

        class TestClass {
            lateinit var a: String

            fun initA(b: String) {
                a = b
            }

            fun checkIsInitialized(): Boolean {
                return ::a.isInitialized
            }
        }

        val testObject = TestClass()
        testObject.checkIsInitialized() shouldBe false
        testObject.initA("hello")
        testObject.checkIsInitialized() shouldBe true
    }
})