import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class ExpressionTuple(val cond: Int, val expected: Int)

class Conditionals : FunSpec({
    context("if is an expression, not a statement, it can return a value, and if you use it as an expression it must have and else clause") {
        withData(
            ExpressionTuple(10, 1),
            ExpressionTuple(-3, -1)
        ){
            (cond, expected) -> {
                val value = if (cond > 0 ) {
                    1
                }
                else {
                    -1
                }

                value shouldBe expected
            }
        }
    }

    context("if all the bodies contains only a single statement, you can skip braces. This is called expression-style if") {
        withData(
            ExpressionTuple(10, 1),
            ExpressionTuple(-3, -1)
        ){
                (cond, expected) -> {
                    val value = if (cond > 0 ) 1 else -1

                    value shouldBe expected
                }
        }
    }
})