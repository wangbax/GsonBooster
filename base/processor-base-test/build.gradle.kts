plugins {
    kotlin("jvm")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
    }
}

dependencies {
    api(Dependencies.junit)
    api(Dependencies.gson)
    api(Dependencies.kotlin_compiler_testing)

    api(project(":booster-annotation"))
}