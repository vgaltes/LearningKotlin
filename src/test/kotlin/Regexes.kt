import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Regexes : StringSpec({
    "you can use matches" {
        val regex = Regex("cat")

        regex.matches("cat") shouldBe true
        regex.matches("dog") shouldBe false
        regex.matches("cats") shouldBe false
    }

    "you can use the dot character that means any character" {
        val regex = Regex("cat.") // creating the "cat." regex

        val stringCat = "cat."
        val stringEmotionalCat = "cat!"
        val stringCatWithSpace = "cat "
        val stringCatN = "cat\n"
        val stringCatB = "cat\b"
        val stringCatT = "cat\t"

        regex.matches(stringCat) shouldBe true
        regex.matches(stringEmotionalCat) shouldBe true
        regex.matches(stringCatWithSpace) shouldBe true
        regex.matches(stringCatN) shouldBe false
        regex.matches(stringCatB) shouldBe true
        regex.matches(stringCatT) shouldBe true
    }

    "you can use the question mark character, that means that the preceding character is optional" {
        val regex = Regex("cat.?")
        val stringCat = "cat."
        val stringEmotionalCat = "cat!"
        val stringCatWithSpace = "cat "
        val stringCot = "cot"

        regex.matches(stringCat) shouldBe true
        regex.matches(stringEmotionalCat) shouldBe true
        regex.matches(stringCatWithSpace) shouldBe true
        regex.matches(stringCot) shouldBe false
    }

    "you can use the scape character if you need to use a special character in your regex" {
        val regex = Regex("cat\\?")
        val stringCat = "cat"
        val stringEmotionalCat = "cat!"
        val stringQuestioningCat = "cat?"

        regex.matches(stringCat) shouldBe false
        regex.matches(stringEmotionalCat) shouldBe false
        regex.matches(stringQuestioningCat) shouldBe true

        val fourCarrot = Regex("\\\\")
        fourCarrot.matches("\\") shouldBe true
    }

    "you can use sets" {
        val regex = Regex("[bfc]at")

        regex.matches("bat") shouldBe true
        regex.matches("fat") shouldBe true
        regex.matches("cat") shouldBe true
        regex.matches("kat") shouldBe false
    }

    "you can use ranges" {
        var regex = Regex("[0-9]")

        regex.matches("3") shouldBe true

        regex = Regex("[a-zA-Z]")
        regex.matches("c") shouldBe true
        regex.matches("C") shouldBe true
    }

    "you can put several ranges in one set and mix them with separate characters in any order" {
        val regex = Regex("[a-z!?.A-Z]") // matches any letter as well as "!", "?", and "."

        regex.matches("c") shouldBe true
        regex.matches("C") shouldBe true
        regex.matches(".") shouldBe true
        regex.matches("!") shouldBe true
        regex.matches("?") shouldBe true
    }

    "you can use the hat character at the beginning of the set to exclude the characters in that set" {
        val regex = Regex("[^abc]")

        regex.matches("a") shouldBe false
        regex.matches("b") shouldBe false
        regex.matches("c") shouldBe false
        regex.matches("d") shouldBe true
    }

    "you can use the hat character at the beginning of the range to exclude the characters in that range" {
        val regex = Regex("[^a-c]")

        regex.matches("a") shouldBe false
        regex.matches("b") shouldBe false
        regex.matches("c") shouldBe false
        regex.matches("d") shouldBe true
    }

    "to match the hyphen character, we should put it in the first or in the last position in the set" {
        var regex = Regex("[-a-c]")

        regex.matches("a") shouldBe true
        regex.matches("b") shouldBe true
        regex.matches("c") shouldBe true
        regex.matches("d") shouldBe false
        regex.matches("-") shouldBe true

        regex = Regex("[A-C-]")

        regex.matches("A") shouldBe true
        regex.matches("B") shouldBe true
        regex.matches("C") shouldBe true
        regex.matches("D") shouldBe false
        regex.matches("-") shouldBe true
    }

    "hat ^ does not need to be avoided if placed anywhere but the beginning" {
        val regex = Regex("[^abc^]")

        regex.matches("a") shouldBe false
        regex.matches("b") shouldBe false
        regex.matches("c") shouldBe false
        regex.matches("d") shouldBe true
        regex.matches("^") shouldBe false
    }

    "square brackets should always be escaped" {
        val regex = Regex("[\\[\\]]")

        regex.matches("[") shouldBe true
        regex.matches("]") shouldBe true
        regex.matches("D") shouldBe false
    }

    "you can describe alternations with the vertical bar" {
        val regex = Regex("yes|no|maybe")

        regex.matches("yes") shouldBe true
        regex.matches("no") shouldBe true
        regex.matches("maybe") shouldBe true
        regex.matches("y") shouldBe false
    }
})