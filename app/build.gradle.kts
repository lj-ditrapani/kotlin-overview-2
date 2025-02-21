import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    id("org.jetbrains.kotlinx.kover").version("0.9.1")
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

kover {
    reports {
        verify {
            rule {
                bound {
                    aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                    coverageUnits = CoverageUnit.BRANCH
                    minValue = 77
                }
            }
        }
    }
}
