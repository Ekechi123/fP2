plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Apply the 'kapt' plugin for annotation processing (needed for Glide)
}

android {
    namespace = "com.example.fp2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fp2"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // Enable View Binding
    viewBinding {
        enable = true
    }
}

dependencies {
    // Standard dependencies
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation(libs.androidx.runner)
    kapt("com.github.bumptech.glide:compiler:4.15.0")

    // Other dependencies
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Add Lifecycle KTX dependency for lifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")  // or latest version

    // Add Kotlin Coroutines dependency for background tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")  // or latest version

    // Add OkHttp dependency for networking
    implementation("com.squareup.okhttp3:okhttp:4.10.0")  // or latest version

    // Add JSON dependency (org.json is used here)
    implementation("org.json:json:20210307")  // or latest version

    // Unit testing and Android testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
