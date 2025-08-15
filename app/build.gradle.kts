

// File: app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)

}



android {
    namespace = "com.ur4nium.daksh19"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ur4nium.daksh19"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.annotation)
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")




    // Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Firebase BoM (import it first, no version needed here)
    implementation(platform(libs.firebase.bom))

    // Firebase Auth and Firestore without versions (managed by BoM)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation("io.coil-kt:coil:2.6.0")

    // Google Sign-In
    implementation(libs.play.services.auth)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson converter for converting JSON data to Kotlin objects
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines for handling background tasks smoothly
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")




        // ... any other dependencies you already have ...

        // --- ADD THIS NEW LINE AT THE END ---
        implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")




    // For Kotlin (build.gradle.kts)
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")




    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
