import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RegexShorthands : StringSpec({
    "any digit" {
        val regex = Regex("\\d")

        regex.matches("1") shouldBe true
        regex.matches("a") shouldBe false
    }

    "non digit" {
        val regex = Regex("\\D")

        regex.matches("1") shouldBe false
        regex.matches("a") shouldBe true
    }

    "whitespace character" {
        val regex = Regex("\\s") // short for [ \t\n\x0B\f\r]

        regex.matches(" ") shouldBe true
        regex.matches("\t") shouldBe true
    }

    "non-whitespace character" {
        val regex = Regex("\\S") // short for [^ \t\n\x0B\f\r]

        regex.matches(" ") shouldBe false
        regex.matches("\t") shouldBe false
    }

    "alphanumeric character" {
        val regex = Regex("\\w") // short for [a-zA-Z_0-9]

        regex.matches("a") shouldBe true
        regex.matches("A") shouldBe true
        regex.matches("1") shouldBe true
    }

    "non-alphanumeric character" {
        val regex = Regex("\\W") // short for [^a-zA-Z_0-9]

        regex.matches("a") shouldBe false
        regex.matches("A") shouldBe false
        regex.matches("1") shouldBe false
    }

    /*"word boundary" {
        var regex = Regex("\\ba") // matches all words (sequences of alphanumeric characters) starting with "a"

        regex.find("antonia paquita paquitaantonia antoniapaquita")?.groupValues?.size shouldBe 1
        regex.find("antonia paquita paquitaantonia antoniapaquita")?.groupValues?.first() shouldBe "antonia"

        regex = Regex("laura\\b") // matches all words (sequences of alphanumeric characters) ending with "a"

        regex.find("laura antonio")?.groupValues?.size shouldBe 1
        regex.find("laura antonio")?.groupValues?.first() shouldBe "laura"
    }*/

})