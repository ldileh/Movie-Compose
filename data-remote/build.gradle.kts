plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ldileh.movie.data.remote"
    compileSdk {
        version = release(36)
    }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // coroutine
    implementation(libs.kotlinx.coroutines.core)

    // unit test
    testImplementation(libs.bundles.unit.test)
    androidTestImplementation(libs.bundles.android.test)

    // network dependencies (retrofit & okhttp)
    implementation(libs.bundles.networking)
    ksp(libs.moshi.codegen)

    // javax inject (enable to call annotation @Inject in kotlin)
    implementation(libs.javax.inject)
}