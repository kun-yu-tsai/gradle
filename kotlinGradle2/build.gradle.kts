import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "kotlinGradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("distribution") {
    doLast {
        println("We build the zip with version=$version")
    }
}

tasks.register("release") {
    dependsOn("distribution")
    doLast {
        println("We release now")
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        "classpath"(group = "commons-codec", name = "commons-codec", version = "1.2")
    }
}

gradle.taskGraph.whenReady {
    version =
        if (hasTask(":release")) "1.0"
        else "1.0-SNAPSHOT"
}

