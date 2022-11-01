import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RegexQuantifiers : StringSpec({
    "+ quantifier matches one or more characters" {
        val regex = "ca+b".toRegex()

        regex.matches("cab") shouldBe true
        regex.matches("caaaaab") shouldBe true
        regex.matches("cb") shouldBe false
    }

    "* quantifier matches zero or more characters" {
        val regex = "ca*b".toRegex()

        regex.matches("cab") shouldBe true
        regex.matches("caaaaab") shouldBe true
        regex.matches("cb") shouldBe true
    }

    "you can specify exactly the number of repetitions" {
        val regex = "[0-9]{4}".toRegex() // four digits

        regex.matches("6342") shouldBe true
        regex.matches("9034") shouldBe true
        regex.matches("182") shouldBe false
        regex.matches("54312") shouldBe false
    }

    "you can specify a range for the number of repetitions" {
        val regex = "1{2,3}".toRegex()

        regex.matches("1") shouldBe false
        regex.matches("11") shouldBe true
        regex.matches("111") shouldBe true
        regex.matches("1111") shouldBe false
    }

    "you can specify a 'at least' number of occurrences" {
        val regex = "ab{4,}".toRegex()

        regex.matches("abb") shouldBe false //not enough 'b'
        regex.matches("abbbb") shouldBe true
        regex.matches("abbbbbbb") shouldBe true
    }
})