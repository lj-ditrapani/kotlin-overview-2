plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    jacoco
    id("org.jlleitschuh.gradle.ktlint").version("12.1.2")
}

repositories {
    mavenCentral()
}

tasks.run.configure {
    standardInput = System.`in`
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    mainClass = "overview.MainKt"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

ktlint {
    version.set("1.5.0")
}
