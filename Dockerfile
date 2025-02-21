FROM gradle:8.12-jdk23-noble AS build
WORKDIR /app
COPY gradle.properties settings.gradle.kts .editorconfig ./
COPY gradle/libs.versions.toml gradle/
COPY app/build.gradle.kts app/
RUN gradle check
COPY app/ app/
RUN gradle check koverVerify installDist

FROM eclipse-temurin:23-jre-noble AS release
WORKDIR /app
COPY --from=build /app/app/build/install/ ./
CMD ["./app/bin/app"]
