import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.example.lb1"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.lb1"
        minSdk = 26
        targetSdk = 36
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
    kotlin {
      compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11) // Or JvmTarget.JVM_1_8, JvmTarget.JVM_11, etc.
      }
    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
    buildFeatures {
        compose = true
    }
    room {
      schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.compose.runtime.livedata)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.material.icons.core)
  ksp(libs.androidx.room.compiler)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
}