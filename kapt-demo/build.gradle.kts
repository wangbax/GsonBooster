plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.spirytusz.gsonbooster.kapt"
    compileSdk = BuildTools.compileSdkVersion

    defaultConfig {
        applicationId = "com.spirytusz.gsonbooster.kapt"
        minSdk = BuildTools.minSdkVersion
        targetSdk = BuildTools.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Dependencies.kotlin_stdlib)
    implementation(Dependencies.androidx_appcompat)
    implementation(Dependencies.material_design)
    implementation(Dependencies.androidx_constraintlayout)
    testImplementation(Dependencies.junit)

    implementation(Dependencies.gson)
    implementation(project(":booster-annotation"))
    kapt(project(":booster-processor:processor-kapt"))
}

kapt {
    arguments {
        arg("factory", "com.spirytusz.booster.kapt.BoosterTypeAdapterFactory")
    }
}
