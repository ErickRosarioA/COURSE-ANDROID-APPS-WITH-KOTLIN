plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
}

android {
    namespace = "com.developer.edra.project_sleep_tracker_6"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.developer.edra.project_sleep_tracker_6"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    val nav_version = "2.8.2"
    val version_room = "2.5.1"  // Añadir la versión de Room
    val version_coroutine = "1.6.0"  // Añadir la versión de Coroutines

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    implementation("com.jakewharton.timber:timber:5.0.1")

    // Lifecycle y LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")

    // Room
    implementation("androidx.room:room-runtime:$version_room")
    kapt("androidx.room:room-compiler:$version_room")

    // Kotlin Extensions y Coroutines para Room
    implementation("androidx.room:room-ktx:$version_room")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$version_coroutine")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$version_coroutine")

    // Otras dependencias
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}