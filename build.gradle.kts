val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("io.ktor:ktor-server-auth:$ktor_version") //For Authentication
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version") //For auth Token
    implementation("org.jetbrains.exposed:exposed-core:0.37.3") //For database
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3") //For database
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3") //For database
    implementation("org.jetbrains.exposed:exposed-java-time:0.37.3") //For database
    implementation("org.postgresql:postgresql:42.7.2") //For postgresql
    implementation("com.zaxxer:HikariCP:5.0.1") //For create connection for jdbc
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version") //For serialization of JSON data
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version") //For serialization of JSON data
}
