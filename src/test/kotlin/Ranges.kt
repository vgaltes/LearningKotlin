import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class RangeData(val minRange: Int, val maxRange: Int, val value: Int, val isInRange: Boolean)

class Ranges : FunSpec({
    context("two points can define a range") {
        withData(
            RangeData(1, 10, 5, true),
            RangeData(1, 10, 15, false)
        )
        {
            (minRange, maxRange, value, isInRange) ->
            run {
                val within = value in minRange..maxRange
                within shouldBe isInRange
            }
        }
    }

    context("not within") {
        withData(
            RangeData(1, 10, 5, true),
            RangeData(1, 10, 10, true),
            RangeData(1, 10, 15, false)
        )
        {
            (minRange, maxRange, value, isInRange) ->
            run {
                val notWithin = value !in minRange..maxRange
                notWithin shouldBe !isInRange
            }
        }
    }

    test("you can use within with characters") {
        ('c' in 'a'..'d') shouldBe true
    }

    test("you can use within with strings") {
        ("he" in "ha".."hi") shouldBe true
    }

})