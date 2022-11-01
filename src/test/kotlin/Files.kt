import io.kotest.core.spec.style.StringSpec
import io.kotest.engine.spec.tempfile
import io.kotest.matchers.shouldBe
import java.io.File

class Files :StringSpec({
    "you can read a file" {
        val file = tempfile()
        file.writeText("""Kotlin or Java,
Java or C++.""")

        val lines = File(file.absolutePath).readText()

        lines shouldBe "Kotlin or Java,\n" +
                "Java or C++."
    }

    "you can check if a file exists" {
        val fileName = "src/reading.txt"
        val file = File(fileName)

        file.exists() shouldBe false
    }

    "you can read the file line by line" {
        val file = tempfile()
        file.writeText("""Kotlin or Java,
Java or C++.""")

        val lines = File(file.absolutePath).readLines()

        lines.size shouldBe 2
    }

    "forEachLine() is the recommended way of reading large files" {
        val file = tempfile()
        file.writeText("""Line1
Line2
Line3
Line4""")

        var numberOfLines = 0
        File(file.absolutePath).forEachLine { numberOfLines++ }

        numberOfLines shouldBe 4
    }

    "it's better to use FileSeparator" {
        File.separator shouldBe "/"
    }

    "you can write text" {
        val file = tempfile()
        file.writeText("Line1")
        file.writeText("Line2")

        File(file.absolutePath).readText() shouldBe "Line2"
    }

    "you can append text" {
        val file = tempfile()
        file.writeText("Line1")
        file.appendText("Line2")

        File(file.absolutePath).readText() shouldBe "Line1Line2"
    }

    "files and directories" {
        val outDir = File("outDir")
        outDir.exists() shouldBe false
        outDir.mkdir()
        outDir.exists() shouldBe true
        outDir.isDirectory shouldBe true

        val file = outDir.resolve("newFile.txt") // outDir/newFile.txt
        file.exists() shouldBe false
        file.createNewFile()
        file.exists() shouldBe true
        file.isFile shouldBe true

        outDir.deleteRecursively()
    }
})