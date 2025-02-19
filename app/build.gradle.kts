plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
//    id("androidx.room")
}

android {
    namespace = "com.example.notimanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.notimanager"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    testOptions{
        unitTests.all {
            it.useJUnitPlatform()
        }
        packaging {
            resources {
                excludes += "META-INF/*"
                excludes += "win32-x86/attach_hotspot_windows.dll"
                excludes += "META-INF/licenses/ASM"
                excludes += "win32-x86-64/attach_hotspot_windows.dll"
            }

        }
    }
}

dependencies {
    // AndroidX Core 및 Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.kotlin.stdlib)
    // Jackson Json
    implementation (libs.jackson.module.kotlin)

    // 코루틴
    implementation(libs.kotlinx.coroutines.android)

    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)

    // 테스트
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.kotlinx.coroutines.test)

    // kotest
    testImplementation(libs.kotest.runner.junit5.v591)
    testImplementation(libs.kotest.assertions.core.v591)
    androidTestImplementation(libs.kotest.runner.junit5.jvm)

    // 모킹
    testImplementation(libs.mockk.mockk)

    // 디버깅
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room -> SQLite Orm
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)

    // byte buddy
    implementation(libs.byte.buddy)
    testImplementation(libs.byte.buddy.agent)
}