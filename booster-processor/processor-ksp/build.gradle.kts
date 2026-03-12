plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("maven-publish-plugin")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
    }
}

dependencies {
    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.kotlin_poet)
    implementation(Dependencies.kotlin_poet_ksp)
    implementation(Dependencies.gson)

    implementation(project(":booster-annotation"))
    api(project(":base:processor-base"))
    implementation(project(":base:scan:scan-ksp"))
    runtimeOnly(project(":base:check"))
    runtimeOnly(project(":base:gen"))

    implementation(Dependencies.ksp_api)

    testImplementation(Dependencies.kotlin_compiler_testing_ksp)
    testApi(project(":base:processor-base-test"))
}