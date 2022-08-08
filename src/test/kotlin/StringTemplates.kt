import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringTemplates : StringSpec({
    "you can have a template using the dollar sign" {
        val city = "Paris"
        val temp = "24"

        val expected = "Now, the temperature in $city is $temp degrees Celsius."

        expected shouldBe "Now, the temperature in Paris is 24 degrees Celsius."
    }

    "You can use string templates to put the result of an arbitrary expression in a string" {
        val language = "Kotlin"
        val expected = "$language has ${language.length} letters in the name"

        expected shouldBe "Kotlin has 6 letters in the name"
    }
})