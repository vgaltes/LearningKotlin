import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

enum class DangerLevel (val level: Int){
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    fun getLevel() = "Level is $level"
}

class Enums : StringSpec({
    "enums can have properties" {
        DangerLevel.HIGH.level shouldBe 3
    }

    "enums can have methods" {
        DangerLevel.MEDIUM.getLevel() shouldBe "Level is 2"
    }

    "name allows you to get the name of Enum's instance" {
        DangerLevel.LOW.name shouldBe "LOW"
    }

    "values() returns an array of all instances of Enum" {
        var names = arrayOf<String>()
        for(value in DangerLevel.values()){
            names += value.name
        }

        names.joinToString(",") shouldBe "HIGH,MEDIUM,LOW"
    }

    "ordinal contains a position of Enum's instance" {
        DangerLevel.MEDIUM.ordinal shouldBe 1
        DangerLevel.HIGH.ordinal shouldBe 0
        DangerLevel.LOW.ordinal shouldBe 2
    }

    "valueOf() returns an instance of Enum by its name with String type and case sensitivity" {
        val theEnum = DangerLevel.valueOf("HIGH")

        theEnum.name shouldBe "HIGH"
    }
})