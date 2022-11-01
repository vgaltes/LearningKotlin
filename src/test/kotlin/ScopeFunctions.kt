import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScopeFunctions : StringSpec({
    "apply is commonly used for object setting" {
        // The context object is available as this.
        data class Musician(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

        val musician = Musician("Jonny Greenwood").apply {
            instrument = "Harmonica" // here we can also use this.instrument
            band = "Pavement"
        }

        musician.instrument shouldBe "Harmonica"
        musician.band shouldBe "Pavement"
        musician.name shouldBe "Jonny Greenwood"
    }

    "apply returns the context object" {
        data class Musician(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

        val musician = Musician("Jonny Greenwood").apply {
            instrument = "Harmonica" // here we can also use this.instrument
            band = "Pavement"
        }.copy(name = "Thom York")

        musician.name shouldBe "Thom York"
    }

    "also is similar to apply, but it's recommended when you work with the whole object" {
        // The context object is available as it
        val instruments = mutableListOf("Guitar", "Harmonica", "Bass guitar")
        var instrumentList = ""
        instruments
            .also { instrumentList = it.toString() }
            .add("Theremin")

        instrumentList.toString() shouldBe "[Guitar, Harmonica, Bass guitar]"
    }

    "also seems like it executes operations immediately" {
        var a = 10
        var b = 5
        a = b.also { b = a }

        a shouldBe 5
        b shouldBe 10
    }

    "with returns the result of a lambda" {
        // Context object is available as this
        val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
        val firstAndLast = with(musicians) {
            "First - ${first()} last - ${last()}"
        }

        firstAndLast shouldBe "First - Thom York last - Colin Greenwood"
    }

    "we use let when we want to do something with the safety call operator ? and non-null objects" {
        // It returns the result of the lambda
        // Context object is available as it

        fun processNonNullString(str: String): String{
            return str.uppercase()
        }

        val str: String? = "Jonny Greenwood"
        //processNonNullString(str)       // compilation error: str can be null

        val newString = str?.let {
            val newString = processNonNullString(it)      // OK: 'it' is not null inside '?.let { }'
            newString
        }

        newString shouldBe "JONNY GREENWOOD"
    }

    "we use let when we want to enter local variables with a limited scope" {
        val musicians = listOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
        val modifiedFirstItem = musicians.first().let { firstItem ->
            println("The first item of the list is '$firstItem'")
            if (firstItem.length >= 15) firstItem else "!" + firstItem + "!"
        }.uppercase()

        modifiedFirstItem shouldBe "!THOM YORK!"
    }

    "we use run when we want to initialize a new object and pass the result of a lambda to it" {
        // Context object is available as this.
        // It returns the result of a lambda.

        class MultiportService(var url: String, var port: Int) {
            fun prepareRequest(): String = "Default request"
            fun query(request: String): String = "Result for query '$request'"
        }

        val service = MultiportService("https://example.kotlinlang.org", 80)

        val result = service.run {
            port = 8080
            query(prepareRequest() + " to port $port")
        }

        result shouldBe "Result for query 'Default request to port 8080'"
    }

    "we use run when we want to use a function without an extension and execute a block of several operators" {
        data class Musician(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

        val musician = run {
            val name = "Dave Grohl"
            val band = "Foo Fighters"

            Musician(name = name, band = band)
        }

        musician.name shouldBe "Dave Grohl"
        musician.band shouldBe "Foo Fighters"
    }
})