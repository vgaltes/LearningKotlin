import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class JumpsAndReturns : StringSpec({
    "a break expression is used to terminate the nearest enclosing loop" {
        val terminating = 3
        var result = 0

        while (true) {
            result++
            if (result == terminating)
                break
        }

        result shouldBe terminating
    }

    "continue lets us proceed to the next iteration of the enclosing loop." {
        val origin = "HelloWorld"
        var result = ""

        for(i in origin){
            if ( i == 'o') continue
            result += i
        }

        result shouldBe "HellWrld"
    }

    "break and continue only work for inner loops" {
        val origin = "HelloWorld"
        var result = ""

        for (j in 1..3) {
            for (i in origin) {
                if (i == 'o') continue
                if ( i == 'l') break
                result += "$j$i"
            }
        }

        result shouldBe "1H1e2H2e3H3e"
    }

    "you can use labels to break outer loops" {
        var result = ""
        loop@ for (i in 1..3) {
            for (j in 1..3) {
                result += "$i$j"
                if (j == 3) break@loop
            }
        }

        result shouldBe "111213"
    }

    "you can use labels to continue outer loops" {
        var result = ""
        loop@ for (i in 1..3) {
            for (j in 1..3) {
                for (k in 1..3) {
                    if (k == 2) continue@loop
                    result += "$i$j$k"
                }
            }
        }

        result shouldBe "111211311"
    }

    "you can use break and continue in when expressions" {
        var result = ""

        for (i in 1..10) {
            when (i) {
                3 -> continue
                6 -> break
                else -> result += i
            }
        }

        result shouldBe "1245"
    }

    "the return statement lets us return the result to the nearest enclosing function" {
        fun fillResult() : String {
            var result = ""
            for (i in 1..10) {
                for (j in 1..10) {
                    result += "$i$j"
                    if (j == 3) return result
                }
            }

            return result
        }

        fillResult() shouldBe "111213"
    }
})