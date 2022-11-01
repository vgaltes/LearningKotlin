import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldNotContainInOrder
import io.kotest.matchers.collections.shouldNotMatchInOrder
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class CollectionsAndPredicates: StringSpec({
    "a predicate is a function that returns true or false based on the input" {
        val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }
        isEven(2) shouldBe true
    }

    "you can check the elements of a collection based on predicates" {
        val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }

        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        numbers.any(isEven) shouldBe true
        numbers.any { isEven(it) } shouldBe true
    }

    "you can sort a collection" {
        val list = listOf(1, 4, 3, 2)
        val orderedAsc = list.sorted()
        val orderedDesc = list.sortedDescending()

        orderedAsc.toString() shouldBe "[1, 2, 3, 4]"
        orderedDesc.toString() shouldBe "[4, 3, 2, 1]"
    }

    "you can reverse a collection" {
        val list = listOf(1, 4, 3, 2)
        val reversed = list.reversed()
        reversed.toString() shouldBe "[2, 3, 4, 1]"
    }

    "asReversed returns a reference to the original collection" {
        val list = listOf(1, 4, 3, 2)
        val reversed = list.asReversed()
        reversed.toString() shouldBe "[2, 3, 4, 1]"
    }

    "you can shuffle a collection" {
        val list = listOf(1, 4, 3, 2)
        val shuffled = list.shuffled()

        list shouldNotContainInOrder shuffled

        val shuffledAgain = list.shuffled(Random(1))
        list shouldNotContainInOrder shuffledAgain
    }

    "you can filter a collection" {
        val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }
        val list = listOf(1, 4, 3, 2, 5, 6, 7, 8, 9)

        val filtered = list.filter(isEven)
        filtered.toString() shouldBe "[4, 2, 6, 8]"
    }

    "you can filter if the predicate is not applicable" {
        val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val filtered = list.filterNot(isEven)
        filtered.toString() shouldBe "[1, 3, 5, 7, 9]"
    }

    "you can filter using the index" {
        val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val filtered = list.filterIndexed { index, i -> index > 3 && isEven(i)  }
        filtered.toString() shouldBe "[6, 8]"
    }

    "you can filter by type" {
        val elements = listOf(null, 0, "string", 3.14, null, 'c', "Luke")

        val filtered = elements.filterIsInstance<String>()
        filtered.toString() shouldBe "[string, Luke]"
    }

    "you can filter nulls" {
        val elements = listOf(null, 0, "string", 3.14, null, 'c', "Luke")

        val filtered = elements.filterNotNull()
        filtered.toString() shouldBe "[0, string, 3.14, c, Luke]"
    }

    "you can split the original collection into two" {
        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val (even, odd) = numbers.partition { x -> x % 2 == 0 }
        even.toString() shouldBe  "[0, 2, 4, 6, 8, 10]"
        odd.toString() shouldBe  "[1, 3, 5, 7, 9]"
    }

    "you can create your own sorting condition" {
        val words = listOf("peter", "anne", "michael", "caroline")

        val orderAsc = words.sortedBy { it.first() }
        orderAsc.toString() shouldBe "[anne, caroline, michael, peter]"

        val orderDesc = words.sortedByDescending { it.first() }
        orderDesc.toString() shouldBe "[peter, michael, caroline, anne"
    }

    "you can create a comparator to be even more specific" {
        val words = listOf("peter", "anne", "michael", "caroline")

        val middleLetterComparator =
            Comparator { str1: String, str2: String -> str1[str1.length / 2] - str2[str2.length / 2] }

        val sortedByMiddleLetter = words.sortedWith(middleLetterComparator)
        sortedByMiddleLetter.toString() shouldBe "[michael, caroline, anne, peter]"
    }

    "you can create the comparator on the fly using the compareBye function" {
        val words = listOf("peter", "anne", "michael", "caroline")

        val sortedByMiddleLetter = words.sortedWith(compareBy { it[it.length / 2] })
        sortedByMiddleLetter.toString() shouldBe "[michael, caroline, anne, peter]"
    }

    "reduce operate on the elements of a collection as a sequence and return the accumulated result taking the first element as the initial value" {
        val list = listOf(1, 2, 3, 4, 5)

        val sum = list.reduce { acc, i -> acc + i }

        sum shouldBe 15
    }

    "reduce operate on the elements of a collection as a sequence and return the accumulated result taking a parameter as the initial value" {
        val list = listOf(1, 2, 3, 4, 5)

        val sum = list.fold(2) { acc, i -> acc + i }

        sum shouldBe 17
    }

    "with fold you can change the type of the accumulator" {
        val list = listOf("a", "b", "c", "d", "e")
        val string = list.fold(StringBuilder()) { acc, s -> acc.append(s) }

        string.toString() shouldBe "abcde"
    }

    "can use foldRight() and reduceRight() to apply operations from right to left" {
        // remember that the operation arguments change their order: first goes the element and then the accumulated value
        val list = listOf("a", "b", "c", "d", "e")
        val string = list.foldRight(StringBuilder()) { s, acc -> acc.append(s) }

        string.toString() shouldBe "edcba"
    }

    "you can use the indexes as well" {
        val list = listOf(1, 2, 3, 4, 5)

        val sum = list.reduceIndexed { index, acc, i -> if (index % 2 == 0) acc + i else acc }

        sum shouldBe 9
    }

    "you can deal with empty collections when reducing" {
        val sum = emptyList<Int>().reduceOrNull { acc, i -> acc + i } ?: -2

        sum shouldBe -2
    }

    "you can accumulate the intermediate results" {
        // For this purpose, we have runningFold(), runningReduce(), runningFoldIndexed(), and runningReduceIndexed()
        val list = listOf(1, 2, 3, 4, 5)

        val runningSum = list.runningFold(0) { acc, i -> acc + i }
        runningSum.toString() shouldBe "[0, 1, 3, 6, 10, 15]"
    }

    "you can use basic aggregate functions" {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // Min and Max
        numbers.minOrNull() shouldBe 1
        numbers.maxOrNull() shouldBe 10

        // Average
        numbers.average() shouldBe 5.5

        // Sum
        numbers.sum() shouldBe 55

        // Count
        numbers.count() shouldBe 10
    }

    "you can use the aggregate with a selector function" {
        // for empty collections it will return null
        val words = listOf("anne", "michael", "caroline", "dimitry", "emilio")

        // maxByOrNull/minByOrNull
        words.maxByOrNull { it.length } shouldBe "caroline"
        words.minByOrNull { it.length } shouldBe "anne"

        //maxOfOrNull/minOfOrNull
        words.maxOfOrNull { it.length } shouldBe 8
        words.minOfOrNull { it.length } shouldBe 4

        // sumOf
        words.sumOf { it.length } shouldBe 32

        // using predicates, length > 5
        words.count { it.length > 5 } shouldBe 4
        words.maxByOrNull { it.length > 5 } shouldBe "michael"
        words.minByOrNull { it.length > 5 } shouldBe "anne"
    }

    "you can use a custom comparator" {
        val words = listOf("anne", "michael", "caroline", "dimitry", "emilio")

        // MaxWithOrNull/MinWithOrNull
        words.maxWithOrNull(compareBy { it.length }) shouldBe "caroline"
        words.minWithOrNull(compareBy { it.length }) shouldBe "anne"

        // maxOfWithOrNull/minOfWithOrNull
        words.maxOfWithOrNull(naturalOrder()) { it.length } shouldBe 8
        words.minOfWithOrNull(naturalOrder()) { it.length } shouldBe 4

        // count
        words.count { it.first() == 'a' } shouldBe 1
    }

    "you can group the elements of a collection by a predicate" {
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        // Grouping by the first letter of the name
        val groupedNames = names.groupBy { it.first() }
        groupedNames.toString() shouldBe "{J=[John, Jane, John, Jane], M=[Mary, Mary], P=[Peter, Peter]}"
    }

    "you can use a second lambda as a transformation function" {
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        // Grouping by the first letter of the name
        val groupedNames = names.groupBy( keySelector = { it.first() }, valueTransform = { it.uppercase() } )
        groupedNames.toString() shouldBe "{4=[JOHN, JANE, MARY, JOHN, JANE, MARY], 5=[PETER, PETER]}"
    }

    "you can apply an operation to all groups at the same time and count" {
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        val groupedNames3 = names.groupingBy { it.first() }.eachCount()
        groupedNames3.toString() shouldBe "{J=4, M=2, P=2}"
    }

    "you can apply an operation to all groups at the same time and fold" {
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        val groupedNames3 = names.groupingBy { it.first() }.fold(0) { acc, name -> acc + name.length }
        groupedNames3.toString() shouldBe "{J=16, M=8, P=10}"
    }

    "you can apply an operation to all groups at the same time and reduce" {
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        val groupedNames3 = names.groupingBy { it.length }
            .reduce { _, acc, name -> if (name.length > acc.length) name else acc }
        groupedNames3.toString() shouldBe "{4=John, 5=Peter}"
    }

    "you can apply an operation to all the elements in each group and return the result" {
        // it's the generic form of fold and reduce
        val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

        val groupedNames6 = names.groupingBy { it.first() }
            .aggregate { _, accumulator: Int?, _, first ->
                if (first) 1 else accumulator!! + 1
            }

        groupedNames6.toString() shouldBe "{J=4, M=2, P=2}"

    }
})