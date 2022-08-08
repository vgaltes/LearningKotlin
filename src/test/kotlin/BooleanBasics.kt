import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BooleanBasics : StringSpec ({
    "toBoolean() returns true if the input is \"true\", case insensitive" {
        "true".toBoolean() shouldBe true
        "True".toBoolean() shouldBe true
        "TRUE".toBoolean() shouldBe true
    }

    "toBoolean() returns false if the input is not \"true\", case insensitive" {
        "false".toBoolean() shouldBe false
        "False".toBoolean() shouldBe false
        "T".toBoolean() shouldBe false
    }

    "toBooleanStricxt() returns true if the input is \"true\", case sensitive" {
        "true".toBooleanStrict() shouldBe true

        val exception = shouldThrow<IllegalArgumentException> {
            "True".toBooleanStrict()
        }

        exception.message shouldBe "The string doesn't represent a boolean value: True"
    }

    "toBooleanStrictOrNull() returns true if the input is \"true\", case sensitive, otherwise returns null" {
        "true".toBooleanStrictOrNull() shouldBe true

        "True".toBooleanStrictOrNull() shouldBe null
    }
})