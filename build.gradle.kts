plugins {
    kotlin("jvm").version("1.3.50")
    application
    jacoco
    id("io.gitlab.arturbosch.detekt").version("1.0.1")
    id("org.jlleitschuh.gradle.ktlint").version("8.2.0")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "overview.MainKt"
}

val run: JavaExec by tasks
run.standardInput = System.`in`

tasks.test {
    testLogging {
        events("passed", "started", "failed", "skipped")
    }
}

detekt {
    toolVersion = "1.0.1"
    input = files("src/main/kotlin")
    filters = ".*/resources/.*,.*/build/.*"
}

ktlint {
    version.set("0.34.2")
    enableExperimentalRules.set(true)
}
