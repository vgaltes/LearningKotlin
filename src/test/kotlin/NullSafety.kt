import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class NullSafety: StringSpec({
    "old style" {
        data class Example(val value: String?)
        val obj:Example? = null

        if(obj != null) obj.value shouldBe null
    }

    "safe call" {
        data class Example(val value: String?)
        val obj:Example? = null

        obj?.value shouldBe null
    }

    "Elvis operator" {
        // if the left-hand side of the expression is not null, return it;
        // otherwise, the right-hand side is to return
        var name: String? = "Kotlin"
        var length: Int? = name?.length

        (length ?: 0) shouldBe 6

        name = null
        length = name?.length

        (length ?: 0) shouldBe 0
    }

    "forcing an access to a nullable variable" {
        var name: String? = "Kotlin"
        var length: Int? = name!!.length

        length shouldBe 6

        val exception = shouldThrow<NullPointerException> {
            var name2: String? = null
            name2!!.length
        }

        (exception is NullPointerException) shouldBe true
    }
})