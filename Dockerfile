FROM gradle:8-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN printf 'rootProject.name = "Kflow"\n\npluginManagement {\n    repositories {\n        mavenCentral()\n        gradlePluginPortal()\n    }\n}\n\ndependencyResolutionManagement {\n    repositories {\n        mavenCentral()\n    }\n}\n\ninclude(":server")' > settings.gradle.kts
RUN gradle :server:buildFatJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/server/build/libs/server-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
