Kotlin Overview 2
=================

A follow-up to Part 1
[Kotlin Overview](https://github.com/lj-ditrapani/kotlin-overview).
This implements the same todo app as in the
[TypeScript Overview](https://github.com/lj-ditrapani/typescript-overview).


Building and running an application on the JVM
----------------------------------------------

- run `./gradlew distTar` or `./gradlew distZip`;
  creates tar or zip application files under app/build/distributions/
- run `./gradlew installDist`;
  unzips files into build/install/kotlin-overview-2.
  Run as `./app/build/install/app/bin/app`


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

    ./gradlew run --console=plain

Run tests

    ./gradlew test

Run test coverage report

    ./gradlew test jacocoTestReport
    firefox build/reports/jacoco/test/html/overview/index.html

Run tests and static analysis

    ./gradlew check

Format code

    ./gradlew ktlintFormat

Create tar (or zip) application file under app/build/distributions/

    ./gradlew distTar
    ./gradlew distZip

Install and run artifact

    ./gradlew installDist && ./app/build/install/app/bin/app
