plugins {
    kotlin("jvm") version Versions.kotlin_version apply false
    id("com.google.devtools.ksp") version Versions.ksp apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Dependencies.gradle_build_tool)
        classpath(kotlin("gradle-plugin", version = Versions.kotlin_version))
        classpath(Dependencies.androidx_benchmark_gradle_plugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    group = ext["GROUP"].toString()
    version = ext["VERSION"].toString()

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}