import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    application
    id("com.gradleup.shadow") version "8.3.6"
}

java {
    sourceCompatibility = JavaVersion.VERSION_23
    targetCompatibility = JavaVersion.VERSION_23
}

application {
    mainClass.set("dev.denux.Main")
}

group = "dev.denux"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Annotations
    compileOnly("org.projectlombok:lombok:1.18.36")
    implementation("org.jetbrains:annotations:24.0.0")

    // Crypto implementation
    implementation("org.bouncycastle:bcprov-jdk18on:1.80")
    implementation("org.springframework.security:spring-security-crypto:6.4.5")

    // DB Driver
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.3")

    // Logger implementation -> needed to get spring-security-crypto to work
    implementation("commons-logging:commons-logging:1.3.5")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

val jar: Jar by tasks
val shadowJar: ShadowJar by tasks
val build: Task by tasks

build.apply {
    dependsOn(jar)
    dependsOn(shadowJar)
}
