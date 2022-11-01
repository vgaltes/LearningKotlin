import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.SimpleTimeZone

interface Animal {
    val numberOfLegs: Int
    fun move(): String
}

interface ComplexAnimal {
    val numberOfLegs: Int
    fun move(): String {
        return "Animal moving"
    }
    fun communicate(): String {
        return "Animal communicating"
    }
}

interface SimpleAnimal {
    val numberOfLegs: Int
        get() = 4
}

interface FirstInterface {
    fun f(): String { return "First" }
    fun g(): String { return "g from first" }
}

interface SecondInterface {
    fun f(): String { return "Second" }
    fun g(): String { return "g from second" }
}

class Interfaces : StringSpec({
    "we can declare an interface and use it in a class" {
        class Dog : Animal {
            override val numberOfLegs: Int
                get() = 4

            override fun move(): String = "Dog moving"
        }

        val dog = Dog()
        dog.numberOfLegs shouldBe 4
        dog.move() shouldBe "Dog moving"
    }

    "interfaces can have default implementations" {
        class Dog : ComplexAnimal {
            override val numberOfLegs: Int
                get() = 4

            override fun communicate(): String {
                return "Bark"
            }
        }

        val dog = Dog()
        dog.numberOfLegs shouldBe 4
        dog.communicate() shouldBe "Bark"
        dog.move() shouldBe "Animal moving"
    }

    "you can use getters in an interface, but not setters" {
        class Dog : SimpleAnimal
        val dog = Dog()
        dog.numberOfLegs shouldBe 4
    }

    "if a class implement two interfaces and both of them have default implementations, we need to specify which implementation to use" {
        class ThirdClass : FirstInterface, SecondInterface {
            override fun f(): String {
                return "${super<FirstInterface>.f()} ${super<SecondInterface>.f()}"
            }

            override fun g(): String {
                return super<SecondInterface>.g()
            }
        }

        val third = ThirdClass()
        third.f() shouldBe "First Second"
    }
})