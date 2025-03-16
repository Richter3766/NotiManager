plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("androidx.room")
    id("org.jetbrains.kotlinx.kover")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.notimanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.notimanager"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "admob_app_id", System.getenv("ADMOB_APP_ID") ?: "")
        resValue("string", "admob_banner_id", System.getenv("ADMOB_BANNER_ID") ?: "")
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

        unitTests.isReturnDefaultValues = true
        packaging {
            resources {
                excludes += "META-INF/*"
                excludes += "win32-x86/attach_hotspot_windows.dll"
                excludes += "META-INF/licenses/ASM"
                excludes += "win32-x86-64/attach_hotspot_windows.dll"
            }

        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

//composeCompiler {
//    reportsDestination = layout.buildDirectory.dir("compose_compiler")
//    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
//}

kover {
    reports{
        filters{
            excludes.packages(listOf(
                "*.source",
                "dagger",
                "hilt_aggregated_deps",
                "*.di",
                "*.presentation"
                    ))
            excludes.classes(listOf(
                "*_*Factory*",
                "*_Factory*",
                "Hilt_*",
                "*_Hilt*",
                "*_MembersInjector"
            ))
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
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.datastore)
    implementation(libs.protolite.well.known.types)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

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
    androidTestImplementation(libs.io.mockk.mockk.android)

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

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    // hilt test
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    // 구글 광고
    implementation(libs.play.services.ads)
}