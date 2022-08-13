import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion = "5.4.1"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
}

tasks.withType<Test>{
    useJUnitPlatform()

    testLogging {
        events(FAILED, STANDARD_ERROR, SKIPPED)

        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}