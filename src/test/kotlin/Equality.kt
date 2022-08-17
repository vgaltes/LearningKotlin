import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

data class TestClass (val property1: String, val property2: Int)

class Equality : StringSpec({
    "structural equality of variables implies equality of inner states." {
        val str1 = "Hello"
        val str2 = "Hello"

        (str1 == str2) shouldBe true
    }

    "referential equality of variables means that these variables point to the same object" {
        val o1 = TestClass("object 1", 1)
        val o2 = o1
        val o3 = TestClass("object 1", 1)

        (o1 == o2) shouldBe true
        (o1 === o2) shouldBe true // they point to the same object
        (o1 === o3) shouldBe false
    }

    "immutable objects point to the same variable, but not if you change them" {
        var o1 = 2
        var o2 = 2

        (o1 === o2) shouldBe true

        o1++

        (o1 === o2) shouldBe false
    }

})