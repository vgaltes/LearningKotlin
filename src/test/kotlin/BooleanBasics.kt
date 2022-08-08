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

    "not reverses the Boolean value" {
        val value = true

        !value shouldBe false
    }

    "and returns true if both operands are true, otherwise returns false" {
        (true && true) shouldBe true
        (false && true) shouldBe false
        (true && false) shouldBe false
        (false && false) shouldBe false
    }

    "or returns true if at least one operand is true, otherwise returns false" {
        (true || true) shouldBe true
        (false|| true) shouldBe true
        (true || false) shouldBe true
        (false || false) shouldBe false
    }

    "xor returns true if both operands have different values, otherwise returns false" {
        (true xor true) shouldBe false
        (false xor true) shouldBe true
        (true xor false) shouldBe true
        (false xor false) shouldBe false
    }

    "not takes precedence over and" {
        (true && !false) shouldBe true
    }
})