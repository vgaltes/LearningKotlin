import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class WhenData(val optionChosen: Int, val expected: String)
data class WhenConditionalChecking(val value1: Int, val value2: Int, val result: Int, val expected: String)

class WhenExpression : FunSpec({
    context("simple when") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "two"),
            WhenData(4, "default")
        ) { (optionChosen, expected) ->
            run {
                var result = " "
                when (optionChosen) {
                    1 -> result = "one"
                    2 -> result = "two"
                    3 -> result = "three"
                    else -> result = "default"
                }

                result shouldBe expected
            }
        }
    }

    context("you can use complex blocs") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "twotwo"),
            WhenData(4, "defaultdefaultdefault")
        ) { (optionChosen, expected) ->
            run {
                var result = " "
                when (optionChosen) {
                    1 -> {
                        val str = "one"
                        result = str
                    }

                    2 -> {
                        val str = "twotwo"
                        result = str
                    }

                    3 -> {
                        val str = "threethreethree"
                        result = str
                    }

                    else -> {
                        val str = "defaultdefaultdefault"
                        result = str
                    }
                }

                result shouldBe expected
            }
        }
    }

    context("when as a expression") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "two"),
            WhenData(4, "default")
        ) { (optionChosen, expected) ->
            run {
                val result = when (optionChosen) {
                    1 -> "one"
                    2 -> "two"
                    3 -> "three"
                    else -> "default"
                }

                result shouldBe expected
            }
        }
    }

    context("multiple options") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "two"),
            WhenData(3, "two"),
            WhenData(4, "default")
        ) { (optionChosen, expected) ->
            run {
                val result = when (optionChosen) {
                    1 -> "one"
                    2,3 -> "two"
                    else -> "default"
                }

                result shouldBe expected
            }
        }
    }

    context("when when is a expression and we use multiple statements, the last statement should be the value returned ") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "two"),
            WhenData(4, "default")
        ) { (optionChosen, expected) ->
            run {
                val result = when (optionChosen) {
                    1 -> {
                        val str = "one"
                        str
                    }

                    2 -> {
                        val str = "two"
                        str
                    }

                    3 -> {
                        val str = "three"
                        str
                    }

                    else -> {
                        val str = "default"
                        str
                    }
                }

                result shouldBe expected
            }
        }
    }

    context("conditional checking") {
        withData(
            WhenConditionalChecking(5, 3, 2, "2 equals 5 minus 3"),
            WhenConditionalChecking(0, 0, 0, "0 equals 0 plus 0")
        ) { (a, b, result, expected) ->
            run {
                val result = when (result) {
                    a + b -> "$result equals $a plus $b"
                    a - b -> "$result equals $a minus $b"
                    a * b -> "$result equals $a times $b"
                    else -> "We do not know how to calculate $result"
                }

                result shouldBe expected
            }
        }
    }

    context("ranges") {
        withData(
            WhenData(0, "zero"),
            WhenData(1, "one"),
            WhenData(25, "twenty five"),
            WhenData(26, "default")
        ) { (optionChosen, expected) ->
            run {
                val result = when (optionChosen) {
                    0 -> "zero"
                    in 1..10 -> "one"
                    in 11..25 -> "twenty five"
                    else -> "default"
                }

                result shouldBe expected
            }
        }
    }

    context("without arguments") {
        withData(
            WhenData(1, "one"),
            WhenData(2, "two"),
            WhenData(4, "default")
        ) { (optionChosen, expected) ->
            run {
                val result = when {
                    optionChosen == 1 -> "one"
                    optionChosen == 2 -> "two"
                    optionChosen == 3 -> "three"
                    else -> "default"
                }

                result shouldBe expected
            }
        }
    }
})