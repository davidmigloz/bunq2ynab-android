plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileSdkVersion(AndroidSdk.compileSdkVersion)

    defaultConfig {
        applicationId = "app.bunq2ynab"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(AppDeps.kotlinStdLib)
    implementation(AppDeps.kotlinXCoroutines)
    implementation(AppDeps.hilt)
    kapt(AppDeps.hiltCompiler)
    implementation(AppDeps.androidXCore)
    implementation(AppDeps.androidXAppCompat)
    implementation(AppDeps.androidXFragment)
    implementation(AppDeps.androidXConstraintLayout)
    implementation(AppDeps.androidXLifecycleCommon)
    implementation(AppDeps.androidXLifecycleViewModel)
    implementation(AppDeps.androidXLifecycleViewModelSaveState)
    implementation(AppDeps.androidXLifecycleLiveData)
    implementation(AppDeps.androidXLifecycleService)
    implementation(AppDeps.androidXLifecycleProcess)
    implementation(AppDeps.androidXHiltLifecycleViewModel)
    kapt(AppDeps.androidXHiltCompiler)
    implementation(AppDeps.androidXNavigationFragment)
    implementation(AppDeps.androidXNavigationUI)
    implementation(AppDeps.materialComponents)
    testImplementation(TestDeps.junit)
}
