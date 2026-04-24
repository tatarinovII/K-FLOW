plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    kotlin("plugin.serialization")
    application
}

group = "my.tatarinov.kflow"
version = "1.0.0"
application {
    mainClass.set("my.tatarinov.kflow.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {

    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor")

    implementation("org.jetbrains.exposed:exposed-core:1.1.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:1.1.1")
    implementation("org.jetbrains.exposed:exposed-dao:1.1.1")
    implementation("org.postgresql:postgresql:42.7.7")
    implementation("org.jetbrains.exposed:exposed-java-time:1.1.1")

    // build.gradle.kts
    implementation("io.ktor:ktor-server-auth:${ktor}")
    implementation("io.ktor:ktor-server-auth-jwt:${ktor}")

    implementation("org.mindrot:jbcrypt:0.4")
    implementation("com.google.api-client:google-api-client:2.2.0")
}