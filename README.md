Kotlin Overview 2
=================


Building and running an application on the JVM
----------------------------------------------

- run `./gradlew assembleDist`;
  creates zip and tar application files under build/distributions/
- run `./gradlew installDist`;
  unzips files into build/install/kotlin-overview-2.
  Run as `./build/install/kotlin-overview-2/bin/kotlin-overview-2`.


Develop this overview
=====================

Run app

    ./gradlew run

Run tests

    ./gradlew test

Run tests and static analysis

    ./gradlew check

Format code

    ./gradlew ktlintFormat

Run static analysis

    ./gradlew detekt

Create zip and tar application files under build/distributions/

    ./gradlew assembleDist

Unzips dist files into build/install/.

    ./gradlew installDist

Run main

    ./build/install/kotlin-overview-2/bin/kotlin-overview-2
