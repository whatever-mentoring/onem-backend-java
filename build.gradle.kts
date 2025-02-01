import org.asciidoctor.gradle.jvm.AsciidoctorTask

plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.asciidoctor.jvm.convert") version "4.0.0"
}

group = "community.whatever"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")


    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


val snippetsDir = file("build/generated-snippets")

tasks.withType<Test> {
    useJUnitPlatform()
    outputs.dir(snippetsDir)
}



tasks.named<AsciidoctorTask>("asciidoctor") {
    dependsOn(tasks.test)
    inputs.dir(snippetsDir)

    baseDirFollowsSourceFile()
}