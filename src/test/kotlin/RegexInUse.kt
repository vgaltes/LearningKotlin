import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RegexInUse: StringSpec({
    "you can use regexes in the split method" {
        val number = "+1-213-345-6789"
        val brackets = "+1-(213)-345-6789"
        // splitting all substrings in number with brackets
        val splitBrackets = brackets.split("(-\\(|\\)-|-)".toRegex())
        splitBrackets shouldBe listOf("+1", "213", "345", "6789")
        // splitting only two substring
        val splitFirstBrackets = number.split("(-\\(|\\)-|-)".toRegex(), 2)
        splitFirstBrackets shouldBe listOf("+1", "213-345-6789")
        // splitting all substrings in number without brackets
        val splitNumber = number.split("(-\\(|\\)-|-)".toRegex())
        splitNumber shouldBe listOf("+1", "213", "345", "6789")
    }

    "you can use regexes in the replace method" {
        val withDigits = "The first test flight of Falcon 9 was on June 4, 2010, " +
                "from Cape Canaveral, Florida, and the first resupply mission " +
                "to the ISS was made on October 7, 2012."
        val processedString = withDigits.replace("\\d+".toRegex(), "[digits]")

        processedString shouldBe "The first test flight of Falcon [digits] was on June [digits], [digits], " +
                "from Cape Canaveral, Florida, and the first resupply mission " +
                "to the ISS was made on October [digits], [digits]."
    }

    "you can use find and findAll methods to find matches of a regular expression" {
        val regex = """\d{4}-\d{2}-\d{2}""".toRegex() // date template in format YYYY-MM-DD
        val matchResult =
            regex.findAll("Harry was born 1980-07-31 in the Godric's Hollow."
                    + "In 1997-12-24, on Christmas Eve, he returned there"
                    + "for the legendary Gryffindor sword").toList()

        matchResult.map { it.value } shouldBe listOf("1980-07-31", "1997-12-24")
    }

    "a part of a regular expression can be enclosed in parenthesis forming a group" {
        val regexWithGroups = "(ho)+".toRegex()
        val text = "ho hoho hohoho"

        val resultWithGroups = regexWithGroups.findAll(text)
        resultWithGroups.count() shouldBe 3
        val index = 0
        var group = resultWithGroups.first()
        group.value shouldBe "ho"
        group = group.next()!!
        group.value shouldBe "hoho"
        group = group.next()!!
        group.value shouldBe "hohoho"
    }

    "we can refer to already encountered groups with backslash" {
        val regex = Regex("""\d{4}(-|\/)\d{2}\1\d{2}""") // The \1 refers to the first encountered group
        val dates = regex.findAll("Date 1: 2022-06-06" +
                "Date 2: 2021/01/01; date 3: 2020-02/02; Date 4: 2021/01/1")

        dates.count() shouldBe 2
        var group = dates.first()
        group.value shouldBe "2022-06-06"
        group = group.next()!!
        group.value shouldBe "2021/01/01"
    }

})