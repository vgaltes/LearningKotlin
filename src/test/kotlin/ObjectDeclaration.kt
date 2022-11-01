import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

data class Player (val name: String, val age: Int)

object PlayingField {
    fun getAllPlayers(): Array<Player> {
        return arrayOf(Player("Player1", 25), Player("Player2", 14))
    }

    fun isPlayerOnLegalAge(player: Player): Boolean {
        return player.age >= 18
    }
}

class QuickPlayer(val id: Int) {
    object Properties {
        val defaultSpeed = 7

        fun calculateMovePenalty(cell: Int) : Int {
            return if (cell > 100) 3
            else 0
        }
    }
    val superSpeed = Properties.defaultSpeed * 2
}

class QuickPlayerWithCompanion(val id: Int) {
    companion object Properties {
        val defaultSpeed = 7
    }
    val superSpeed = defaultSpeed * 2
}

class QuickPlayerWithCompanionNameless(val id: Int) {
    companion object {
        val defaultSpeed = 7
    }
    val superSpeed = defaultSpeed * 2
}

object Languages {
    const val FAVORITE_LANGUAGE = "Kotlin" // constants must be of a basic type, cannot have a custom getter, and should be named using the SCREAMING_SNAKE_CASE
}

object Game {
    object Properties {
        val maxPlayersCount = 13
        val maxGameDurationInSec = 2400
    }

    object Info {
        val name = "My super game"
    }
}

class ObjectDeclaration : StringSpec({
    "you can create a singleton using object declaration" {
        val players = PlayingField.getAllPlayers()
        players.size shouldBe 2

        val legalAge = PlayingField.isPlayerOnLegalAge(players[1])
        legalAge shouldBe false
    }

    "you can use object declaration inside a nested class" {
        QuickPlayer.Properties.defaultSpeed shouldBe 7
    }

    "you can use properties from the nested class in the outer one" {
        val player = QuickPlayer(1)
        player.superSpeed shouldBe 14
    }

    "you can create compile time constants inside an object declaration" {
        Languages.FAVORITE_LANGUAGE shouldBe "Kotlin"
    }

    "you can nest object declarations to organize the data" {
        Game.Properties.maxPlayersCount shouldBe  13
    }

    "a companion object is a singleton attached to an outer class" {
        QuickPlayerWithCompanion.defaultSpeed shouldBe 7 // no neeed to access via Properties
        QuickPlayerWithCompanion.Properties.defaultSpeed shouldBe 7
    }

    "you can omit the name of the companion object" {
        QuickPlayerWithCompanionNameless.defaultSpeed shouldBe 7
        QuickPlayerWithCompanionNameless.Companion.defaultSpeed shouldBe 7 // default name
    }
})