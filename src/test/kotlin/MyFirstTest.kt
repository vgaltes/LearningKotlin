import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

class MyFirstTest : StringSpec({
//    "length should return size of string" {
//        "hello".length shouldBe 5
//    }
    "startsWith should test for a prefix" {
        "world" should startWith("wor")
    }
})