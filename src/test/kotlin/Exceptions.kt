import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Exceptions : StringSpec({
    "try is an expression" {
        val value = try { 2 / 0 } catch (e: Exception) {4}
        value shouldBe 4
    }
})