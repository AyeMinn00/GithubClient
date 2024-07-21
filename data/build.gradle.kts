plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.ayeminoo.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)

    implementation(libs.timber)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlin.faker)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)
    testImplementation((libs.kotlinx.serialization.json))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}