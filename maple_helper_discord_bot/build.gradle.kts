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
    // Core
    implementation(project(":maple_helper_core"))

    // Discord
    implementation("net.dv8tion:JDA:5.0.0-beta.16")
}

tasks.test {
    useJUnitPlatform()
}
