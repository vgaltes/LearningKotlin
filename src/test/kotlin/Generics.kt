import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.reflect.typeOf

interface Watchable

class Generics : StringSpec ({
    "you can have a generic function" {
        fun <T> calculateLength(list: List<T>): Int {
            return list.size
        }

        var list = listOf("hello", "from", "hyperskill")

        calculateLength<String>(list) shouldBe 3
    }

    "you can have a generic method" {
        class NonGenericClass {
            fun <T> someGenericMethod(t: T): T {
                return t
            }
        }

        var item = NonGenericClass()
        var value = item.someGenericMethod("Hello!")

        value shouldBe "Hello!"
    }

    "you can have a generic method in a generic class" {
        class GenericClass<T> {
            fun <U> someGenericMethod(t: T, u: U): T {
                return t
            }
        }

        var item = GenericClass<Int>()
        var value = item.someGenericMethod(2, "Hello!")

        value shouldBe 2
    }

    "you can have extension functions of a generic class" {
        class BiggerBox<T>(var value1: T, var value2: T) {}

        fun <T> BiggerBox<T>.changeBoxes() {
            val tmp = this.value1
            this.value1 = this.value2
            this.value2 = tmp
        }

        val box1 = BiggerBox(1, 2)
        box1.changeBoxes()

        box1.value1 shouldBe 2
        box1.value2 shouldBe 1
    }

    "you can use type bonds to restrict the types of the generics" {
        open class Book {}
        class Magazine : Book() {}

        class Storage<T : Book>() {
            val list = mutableListOf<T>()
            fun add(item:T) {
                list.add(item)
            }
        }

        val storage = Storage<Magazine>()
        storage.add(Magazine())
        storage.add(Magazine())

        storage.list.size shouldBe 2
    }

    "you can use type bonds in functions too" {
        open class Book {
            open var name = ""
        }
        class Magazine : Book() {
            override var name: String = "Magazine"
                get() = field
                set(value) { field = value}
        }

        fun <T: Book> returnFirst(list: List<T>): T {
            return list.first()
        }

        val  m1 = Magazine()
        m1.name = "Magazine 1"
        val  m2 = Magazine()
        m2.name = "Magazine 2"
        val list = listOf(m1, m2)

        val first = returnFirst(list)
        first.name shouldBe "Magazine 1"
    }

    "you can have multiple bonds" {
        open class Book
        class Magazine : Book(), Watchable
        class Pamphlet : Book(), Watchable


        fun <T> returnFirstItem(item1: T, item2: T): T where T: Book, T: Watchable{
            return item1
        }

        val item = returnFirstItem(Magazine(), Pamphlet())

        item.shouldBeInstanceOf<Magazine>()
    }
})