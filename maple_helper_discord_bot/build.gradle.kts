plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    // Discord
    implementation("net.dv8tion:JDA:5.0.0-beta.16")

    // Logger
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
