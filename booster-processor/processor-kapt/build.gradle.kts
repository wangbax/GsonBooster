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
    compileOnly(Dependencies.gson)

    implementation(project(":booster-annotation"))
    api(project(":base:processor-base"))
    implementation(project(":base:scan:scan-kapt"))
    runtimeOnly(project(":base:check"))
    runtimeOnly(project(":base:gen"))

    implementation(Dependencies.auto_service_annotation)
    kapt(Dependencies.auto_service)

    testImplementation(Dependencies.kotlin_compiler_testing)
    testApi(project(":base:processor-base-test"))
}