import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

interface MyInterface {
    fun print(): String
    val msg: String
}

interface Level {
    fun getLevel(): Int
}

interface Enemy {
    fun isEnemy(): Boolean
}

interface Class {
    fun getClass(): String
}

object NotDangerous : Level { override fun getLevel(): Int { return 1 } }
object Friend : Enemy { override fun isEnemy(): Boolean { return false } }
object Wizard : Class { override fun getClass(): String { return "Wizard"}}


class Classes : StringSpec({
    "default constructor can be created in class declaration" {
        class Size(val width: Int, val height: Int) { // it makes no sense to specify a default value
            var area: Int = width * height
        }

        val s = Size(1,2)
    }

    "if no constructor is defined, an implicit one is created" {
        class Size {
            var width: Int = 0
            var height: Int = 0
        }

        val s = Size()
        s.width = 1
        s.height = 2
    }

    "you can create add initialization blocks" {
        class Size {
            var width: Int = 0
            var height: Int = 0
            val messages = mutableListOf<String>()


            init {
                messages.add("Initialization code")
            }
        }

        val s = Size()
        s.messages.first() shouldBe "Initialization code"
    }

    "initialization blocks are executed in order of appearance" {
        class Size {
            val messages = mutableListOf<String>()


            init {
                messages.add("Initialization code 1")
            }

            init {
                messages.add("Initialization code 2")
            }

            init {
                messages.add("Initialization code 3")
            }
        }

        val s = Size()
        s.messages.forEachIndexed { index, value -> value shouldBe "Initialization code ${index+1}" }
    }

    "you can create a custom constructor" {
        class Size {
            var width: Int = 0
            var height: Int = 0

            constructor(width: Int, height: Int) {
                this.width = width
                this.height = height
            }
        }

        val s = Size(1, 2)
        s.width shouldBe 1
        s.height shouldBe 2
    }

    "if you have a primary and a custom constructor delegation is mandatory" {
        class Square(var width: Int, var height: Int) {
            var colour: String = "Default"
            constructor(width: Int, height: Int, colour: String) : this(width, height) {
                this.colour = colour
            }
        }

        val s1 = Square(1, 2)
        val s2 = Square(1, 2, "Red")
        s2.width shouldBe 1
        s2.height shouldBe 2
    }

    "constructor execution" {
        class Size(val width: Int, val height: Int) {
            var area: Int = width * height
            val messages = mutableListOf<String>()

            constructor(width: Int, height: Int, outerSize: Size) : this(width, height) {
                outerSize.area -= this.area
                messages.add("custom constructor called")
            }

            init {
                messages.add("primary constructor already called, width is $width")
                messages.add("Initialization called")
            }
        }

        val s1 = Size(1,2)
        s1.messages.first() shouldBe "primary constructor already called, width is 1"
        s1.messages[1] shouldBe "Initialization called"

        val s2 = Size(1, 2, Size(2,3))
        s2.messages.first() shouldBe "primary constructor already called, width is 1"
        s2.messages[1] shouldBe "Initialization called"
        s2.messages[2] shouldBe "custom constructor called"
    }

    "to be able to extend a class we need to open it" {
        open class Book(val pages: Int, val author: String, var cost: Float = 0F) {
            val messages = mutableListOf<String>()

            init {
                messages.add("This is a book by $author, with $pages pages and a cost of $$cost")
            }
        }

        class Comic(pages: Int, author: String, cost: Float = 0F) :Book(pages, author, cost) {
            init {
                messages.add("This is a comic by $author, with $pages pages and a cost of $$cost")
            }
        }

        val book = Book(140, "Kent Beck", 9.99F)
        book.messages[0] shouldBe "This is a book by Kent Beck, with 140 pages and a cost of $9.99"

        val comic = Comic(140, "Kent Beck", 9.99F)
        comic.messages[0] shouldBe "This is a book by Kent Beck, with 140 pages and a cost of $9.99"
        comic.messages[1] shouldBe "This is a comic by Kent Beck, with 140 pages and a cost of $9.99"
    }

    "functions in a class can be opened so they can be overriden" {
        open class Me {
            open fun returnName(name: String): String = "My name is $name"
        }

        class ActuallyMe : Me() {
            override fun returnName(name: String): String = "Actually, my name is $name"
        }

        Me().returnName("Paco") shouldBe "My name is Paco"
        ActuallyMe().returnName("Vicenç") shouldBe "Actually, my name is Vicenç"
    }

    "you can call the base class function" {
        open class Me {
            open fun returnName(name: String): String = "My name is $name"
        }

        class ActuallyMe : Me() {
            override fun returnName(name: String): String = "This is not true -> ${super.returnName(name)}"
        }

        Me().returnName("Paco") shouldBe "My name is Paco"
        ActuallyMe().returnName("Vicenç") shouldBe "This is not true -> My name is Vicenç"
    }

    "toString follows inheritance" {
        open class Tea(val cost: Int, val volume: Int) {
            override fun toString(): String {
                return "cost=$cost, volume=$volume"
            }
        }

        class BlackTea(cost: Int, volume: Int) : Tea(cost, volume)

        val tea = Tea(10, 2)
        tea.toString() shouldBe "cost=10, volume=2"

        val blackTea = BlackTea(12, 3)
        blackTea.toString() shouldBe "cost=12, volume=3"
    }

    "data classes objects are compared by value" {
        data class AThing(val name: String, val value: Int)

        val aThing = AThing("A Thing", 10)
        val anotherThing = AThing("A Thing", 10)

        (aThing == anotherThing) shouldBe true
    }

    "data classes implements toString by default" {
        data class AThing(val name: String, val value: Int)
        val aThing = AThing("A Thing", 10)

        aThing.toString() shouldBe "AThing(name=A Thing, value=10)"
    }

    "only properties defined in the constructor are considered in equals, toString and copy" {
        data class AThing(val name: String, val value: Int) {
            var shape: String = "Default"
        }

        var aThing = AThing("A Thing", 10)
        aThing.shape = "round"
        val anotherThing = AThing("A Thing", 10)
        anotherThing.shape = "square"

        (aThing == anotherThing) shouldBe true
        aThing.toString() shouldBe "AThing(name=A Thing, value=10)"
    }

    "toString can be overriden (and equals) but copy no" {
        data class AThing(val name: String, val value: Int) {
            var shape: String = "Default"

            override fun toString(): String {
                return "The thing $name has a value of $value and a shape like $shape"
            }
        }

        val aThing = AThing("A Thing", 10)
        aThing.shape = "square"
        aThing.toString() shouldBe "The thing A Thing has a value of 10 and a shape like square"
    }

    "you can easily copy an object of a data class" {
        data class AThing(val name: String, val value: Int)
        val aThing = AThing("A Thing", 10)
        val anotherThing = aThing.copy(name="Another Thing")

        (aThing == anotherThing) shouldBe false
        anotherThing.toString() shouldBe "AThing(name=Another Thing, value=10)"
    }

    "you can have a custom getter of a property" {
        class Client {
            val messages = mutableListOf<String>()
            val name = "Unknown"
                get() {
                    messages.add("Custom getter called")
                    return field
                }
        }

        val c = Client()
        c.name shouldBe "Unknown"
        c.messages.first() shouldBe "Custom getter called"


    }

    "you can have a custom setter of a property" {
        class Client {
            var name = "Unknown"
                set(value) {
                    field = "Dr $value"
                }
        }

        val c = Client()
        c.name = "Paco"
        c.name shouldBe "Dr Paco"
    }

    "you can have inner classes" {
        class Cat(val name: String) {
            inner class Bow(val color: String) {
                fun printColor() : String {
                    return "The cat named $name has a $color bow."
                }
            }
        }

        val cat = Cat("Bob")
        val bow: Cat.Bow = cat.Bow("red")

        bow.printColor() shouldBe  "The cat named Bob has a red bow."
    }

    "if the outer class and the inner class have a property with the same name, you can access the elements of the outer class using the qualified this" {
        class Cat(val name: String, val color: String) {
            inner class Bow(val color: String) {
                fun printColor(): String {
                    return "The cat named $name is ${this@Cat.color} and has a $color bow."
                }
            }
        }

        val cat = Cat("Bob", "black")
        val bow = cat.Bow("red")

        bow.printColor() shouldBe "The cat named Bob is black and has a red bow."

    }

    "check throws an illegal exception if the statement is false" {
        data class Cat(val name: String, val color: String, val valid: Boolean) {
            init {
                check(valid)
            }
        }

        val exception = shouldThrow<IllegalStateException> {
            Cat("patata", "red", false)
        }

        exception.message shouldBe "Check failed."
    }

    "you can set a custom message in check" {
        data class Cat(val name: String, val color: String, val valid: Boolean) {
            init {
                check(valid) { "The cat is not valid"}
            }
        }

        val exception = shouldThrow<IllegalStateException> {
            Cat("patata", "red", false)
        }

        exception.message shouldBe "The cat is not valid"
    }

    "require validates the argument passed to the function and throws an IllegalArgumentException if the validation doesn't pass" {
        data class Cat(val name: String, val color: String, val age: Int) {
            init {
                require(age>9)
            }
        }

        val exception = shouldThrow<IllegalArgumentException> {
            Cat("patata", "red", 8)
        }

        exception.message shouldBe "Failed requirement."
    }

    "you can set a custom message in require" {
        data class Cat(val name: String, val color: String, val age: Int) {
            init {
                require(age>9) { "The cat is too young"}
            }
        }

        val exception = shouldThrow<IllegalArgumentException> {
            Cat("patata", "red", 8)
        }

        exception.message shouldBe "The cat is too young"
    }


    "you can use delegates if you want to reuse some of the implementation of an interface by other class" {
        class MyImplementation : MyInterface {
            override fun print(): String {
                return msg
            }

            override val msg: String = "MyImplementation sends regards!"
        }

        class MyNewClass(base: MyInterface) : MyInterface by base {
            override val msg = "Delegate sends regards."
        }

        val a = MyImplementation()
        val c = MyNewClass(a)

        c.msg shouldBe "Delegate sends regards."
    }

    "If you don't override, the delegated class will take control" {
        class MyImplementation : MyInterface {
            override fun print(): String {
                return msg
            }

            override val msg: String = "MyImplementation sends regards!"
        }

        class MyNewClass(base: MyInterface) : MyInterface by base {
            override val msg = "Delegate sends regards."
        }

        val a = MyImplementation()
        val c = MyNewClass(a)
        c.print() shouldBe "MyImplementation sends regards!"
    }

    "with composition, we can combine object declaration and delegation to avoid writing a lot of code" {
        class NotDangerousFriendlyWizard : Level by NotDangerous, Enemy by Friend, Class by Wizard
    }
})