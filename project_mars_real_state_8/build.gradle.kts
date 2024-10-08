plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.developer.edra.project_mars_real_state_8"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.developer.edra.project_mars_real_state_8"
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


    // Retrofit Coroutines Support - Deprecated - No more required
     //implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$version_retrofit_coroutines_adapter"

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.4.0-beta01")

    // Retrofit - Deprecated plugins - No more required
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
     implementation ("com.squareup.retrofit2:converter-scalars:2.11.0")

    // Retrofit with Moshi Converter
    implementation ("com.squareup.retrofit2:converter-moshi:2.11.0")


    // Moshi
    implementation ("com.squareup.moshi:moshi:1.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}