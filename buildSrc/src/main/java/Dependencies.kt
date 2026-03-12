object BuildTools {
    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val minSdkVersion = 21
}

object Versions {
    const val gradle_build_tool = "8.5.0"

    const val kotlin_version = "2.0.21"
    const val kotlin_metadata = kotlin_version

    const val kotlin_poet = "1.18.1"
    const val gson = "2.9.0"
    const val auto_service = "1.1.1"
    const val kotlin_compiler_testing = "0.6.0"
    const val ksp = "$kotlin_version-1.0.28"
    const val junit = "4.13.2"
    const val jmh = "1.28"
    const val androidx_constraintlayout = "2.1.1"
    const val androidx_appcompat = "1.3.1"
    const val material_design = "1.4.0"

    const val androidx_benchmark_gradle_plugin = "1.2.4"
    const val androidx_test_runner = "1.5.2"
    const val androidx_test_junit_ext = "1.1.5"
    const val androidx_benchmark_junit = "1.2.4"

    const val booster = "1.3.0"
}

object Dependencies {
    const val gradle_build_tool = "com.android.tools.build:gradle:${Versions.gradle_build_tool}"

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"

    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"

    const val kotlin_metadata =
        "org.jetbrains.kotlin:kotlin-metadata-jvm:${Versions.kotlin_metadata}"

    const val kotlin_poet = "com.squareup:kotlinpoet:${Versions.kotlin_poet}"

    const val kotlin_poet_ksp = "com.squareup:kotlinpoet-ksp:${Versions.kotlin_poet}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val auto_service_annotation =
        "com.google.auto.service:auto-service-annotations:${Versions.auto_service}"

    const val auto_service = "com.google.auto.service:auto-service:${Versions.auto_service}"

    const val kotlin_compiler_testing =
        "dev.zacsweers.kctfork:core:${Versions.kotlin_compiler_testing}"

    const val ksp_api = "com.google.devtools.ksp:symbol-processing-api:${Versions.ksp}"

    const val kotlin_compiler_testing_ksp =
        "dev.zacsweers.kctfork:ksp:${Versions.kotlin_compiler_testing}"

    const val junit = "junit:junit:${Versions.junit}"

    const val jmh = "org.openjdk.jmh:jmh-core:${Versions.jmh}"

    const val jmh_processor = "org.openjdk.jmh:jmh-generator-annprocess:${Versions.jmh}"

    const val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"

    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"

    const val material_design = "com.google.android.material:material:${Versions.material_design}"

    const val androidx_test_runner = "androidx.test:runner:${Versions.androidx_test_runner}"

    const val androidx_test_junit_ext =
        "androidx.test.ext:junit:${Versions.androidx_test_junit_ext}"

    const val androidx_benchmark_junit =
        "androidx.benchmark:benchmark-junit4:${Versions.androidx_benchmark_junit}"

    const val androidx_benchmark_gradle_plugin =
        "androidx.benchmark:benchmark-gradle-plugin:${Versions.androidx_benchmark_gradle_plugin}"

    const val booster_annotation = "com.spirytusz:booster-annotation:${Versions.booster}"

    const val booster_processor = "com.spirytusz:booster-processor:${Versions.booster}"

    const val booster_processor_ksp = "com.spirytusz:booster-processor-ksp:${Versions.booster}"
}