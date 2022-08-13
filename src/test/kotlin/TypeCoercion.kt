import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TypeCoercion : StringSpec({
    "between Int and Long type gets coerced to Long" {
        val num : Int = 10
        val longNum : Long = 100

        var coerced = num + longNum

        coerced::class.java.typeName shouldBe "long"
    }

    "between Long and Double type gets coerced to Double" {
        val bigNum: Long = 100000
        val doubleNum: Double = 0.0
        val coerced = bigNum - doubleNum

        coerced::class.java.typeName shouldBe "double"
    }

    "operations between bytes get coerced to Int" {
        val one: Byte = 1
        val two: Byte = 2
        val coerced = one + two

        coerced::class.java.typeName shouldBe "int"
    }

    "operations between short get coerced to Int" {
        val one: Short = 1
        val two: Short = 2
        val coerced = one + two

        coerced::class.java.typeName shouldBe "int"
    }

    "operations between bytes and shorts get coerced to Int" {
        val one: Byte = 1
        val two: Short = 2
        val coerced = one + two

        coerced::class.java.typeName shouldBe "int"
    }

    "if we want a byte as a result of the operation between two bytes, we need to manually apply a type conversion" {
        val one: Byte = 1
        val two: Byte = 2
        val result = one + two
        val coerced = result.toByte()

        coerced::class.java.typeName shouldBe "byte"
        this::class.java.simpleName shouldBe "hello"
    }
})