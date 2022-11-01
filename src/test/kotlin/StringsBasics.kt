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

    "you can get a substring (last index is exclusive)" {
        val original = "hello"

        val substring = original.substring(0,3)

        substring shouldBe "hel"
    }

    "you can get a substring from the startIndex only" {
        val original = "hello"

        val substring = original.substring(2)

        substring shouldBe "llo"
    }

    "you can get a substring after or before something" {
        val original = "hello"

        original.substringAfter('l')  shouldBe "lo"
        original.substringBefore('o') shouldBe "hell"
        original.substringBefore('z') shouldBe "hello"
    }

    "you can get a substring after or before the last something" {
        val original = "hello"

        original.substringAfterLast('l')  shouldBe "o"
        original.substringBeforeLast('o') shouldBe "hell"
    }

    "you can replace parts of a string" {
        val greeting = "Good morning!"

        val newGreeting = greeting.replace("morning", "bye")

        newGreeting shouldBe "Good bye!"
    }

    "you can replace only the first occurrence" {
        val sentence = "one one two two"

        val newSentence = sentence.replaceFirst("one", "three")

        newSentence shouldBe "three one two two"
    }

    "you can change the case" {
        val sentence = "a sentence"

        val uppercase = sentence.uppercase()
        uppercase shouldBe "A SENTENCE"

        val lowercase = uppercase.lowercase()
        lowercase shouldBe "a sentence"

    }
})