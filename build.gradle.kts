plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    application
}

group = "org.team9432"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version: String by project

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.auth:google-auth-library-oauth2-http:1.20.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20230815-2.0.0")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}