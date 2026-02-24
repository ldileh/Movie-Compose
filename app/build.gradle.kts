plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")
}

// get some attributes from gradle properties
val movieApiKey: String by project
val movieBaseUrl: String by project

android {
    namespace = "com.ldileh.moviecompose"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.ldileh.moviecompose"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${movieApiKey}\"")
        buildConfigField("String", "BASE_URL", "\"${movieBaseUrl}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            isMinifyEnabled = false

            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    // local modules
    implementation(project(":data"))
    implementation(project(":data-remote"))

    // main dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.kotlin.coroutines)

    // unit test
    testImplementation(libs.bundles.unit.test)
    androidTestImplementation(libs.bundles.android.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.bundles.debug.implementation)

    // dagger hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}