FROM gradle:8-jdk21-alpine AS builder
WORKDIR /app
COPY . .

RUN printf 'plugins {\n    alias(libs.plugins.kotlinJvm) apply false\n    alias(libs.plugins.ktor) apply false\n    kotlin("plugin.serialization") version "2.0.0" apply false\n}' > build.gradle.kts

RUN printf 'rootProject.name = "Kflow"\n\npluginManagement {\n    repositories {\n        mavenCentral()\n        gradlePluginPortal()\n    }\n}\n\ndependencyResolutionManagement {\n    repositories {\n        mavenCentral()\n    }\n}\n\ninclude(":server")' > settings.gradle.kts

RUN gradle :server:buildFatJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/server/build/libs/server-all.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]