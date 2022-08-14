import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RepeatingBlocks : StringSpec({
    "repeat repeats a block and with \"it\" you can access the current iteration" {
        var index = 0

        repeat(3) {
            index++ shouldBe it
        }
    }

    "you can declare a variable in a repeat statement" {
        repeat(3){
            val number = 2
            number shouldBe 2
        }
    }
})