import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringsBasics : StringSpec ({
    "length should return size of string" {
        "hello".length shouldBe 5
    }

    "two strings can be concatenated with +" {
        var string1 = "he"
        var string2 = "llo"

        var concatenation = string1 + string2

        concatenation shouldBe "hello"
    }

    "you can concatenate multiple strings in the same expression" {
        var string1 = "hello"
        var string2 = "world"

        var concatenation = string1 + " " + string2

        concatenation shouldBe "hello world"
    }

    "you can concatenate strings with other values" {
        var concatenation = "He" + "llo" + 123 + true

        concatenation shouldBe "Hello123true"
    }

    "you can concatenate a char with a string" {
        var concatenation = 'H' + "ello"

        concatenation shouldBe "Hello"
    }

// this doesn't compile
//    "you can not concatenate another value with a string" {
//        var concatenation = 123 + "hello"
//    }

    "you can repeat a string" {
        "Hello".repeat(4) shouldBe "HelloHelloHelloHello"
    }
})