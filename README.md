Kotlin Overview 2
=================

A follow-up to Part 1
[Kotlin Overview](https://github.com/lj-ditrapani/kotlin-overview).
This implements the same todo app as in the
[TypeScript Overview](https://github.com/lj-ditrapani/typescript-overview).


Building and running an application on the JVM
----------------------------------------------

- run `./gradlew assembleDist`;
  creates zip and tar application files under build/distributions/
- run `./gradlew installDist`;
  unzips files into build/install/kotlin-overview-2.
  Run as `./build/install/kotlin-overview-2/bin/kotlin-overview-2`.


Todo app Notes
--------------

Files

- src/main/kotlin/overview/
    - `Main.kt`: main loop
    - `Output.kt`: output effects
    - `Todo.kt`: core logic


Develop this overview
=====================

Run app

    ./gradlew run

Run tests

    ./gradlew test

Run test coverage report

    ./gradlew test jacocoTestReport
    firefox build/reports/jacoco/test/html/overview/index.html

Run tests and static analysis

    ./gradlew check

Format code

    ./gradlew ktlintFormat

Run static analysis

    ./gradlew detekt

Create zip and tar application files under build/distributions/

    ./gradlew assembleDist

Package, install and run artifact

    ./gradlew assembleDist installDist && \
        ./build/install/kotlin-overview-2/bin/kotlin-overview-2
