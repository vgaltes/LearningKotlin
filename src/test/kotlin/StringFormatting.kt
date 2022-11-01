import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringFormatting : StringSpec({
    "basic syntax" {
        String.format("%s %s", "Hello", "World") shouldBe "Hello World"
    }

    "another way to use the basic syntax" {
        "%s %s".format("Hello", "World") shouldBe "Hello World"
    }

    "converting a string to upeercase" {
        "%S %S".format("Hello", "World") shouldBe "HELLO WORLD"
    }

    "how to write the % sign" {
        String.format("This is the percentage sign -> %%") shouldBe "This is the percentage sign -> %"
    }

    "add some spacing to those strings" {
        String.format("%8s->%8s", "Hello", "World") shouldBe "   Hello->   World"
        String.format("%-8s->%-8s", "Hello", "World") shouldBe "Hello   ->World   "
    }

    "leading zeros in integers" {
        String.format("%09d", 1234) shouldBe "000001234"
    }

    "thousands divisor" {
        String.format("%,d", 1234) shouldBe "1,234"
    }

    "Number always signed, even if positive." {
        String.format("%+d", 1234) shouldBe "+1234"
    }

    "For a positive number, insert one leading space." {
        String.format("% d", 1234) shouldBe " 1234"
    }

    "Put a negative number in parentheses, without the minus sign." {
        String.format("%(d", -1234) shouldBe "(1234)"
    }

    "Indicate number of decimals in floating numbers" {
        String.format("%.6f", 1234.5678) shouldBe "1234.567800"
    }

    "with g we choose the decimal or the scientific notation, whichever is shorter" {
        String.format("%g", 1000.0) shouldBe "1000.00"
        String.format("%g", 10000000.0) shouldBe "1.00000e+07"
        String.format("%G", 10000000.0) shouldBe "1.00000E+07"
    }

    "formatting booleans" {
        String.format("%b",true) shouldBe "true"
        String.format("%B",true) shouldBe "TRUE"
    }

    "formatting chars" {
        String.format("%c", 'c') shouldBe "c"
        String.format("%C", 'c') shouldBe "C"
    }
})