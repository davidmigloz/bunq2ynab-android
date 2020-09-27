plugins {
    id(GradleDeps.androidApplicationGradlePlugin)
    id(GradleDeps.kotlinAndroidGradlePlugin)
    id(GradleDeps.kotlinAndroidExtensionsGradlePlugin)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = "app.bunq2ynab"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(AppDeps.kotlinStdLib)
    implementation(AppDeps.kotlinxCoroutines)
    implementation(AppDeps.androidxCore)
    implementation(AppDeps.androidxAppCompat)
    implementation(AppDeps.androidxConstraintLayout)
    implementation(AppDeps.materialComponents)
    testImplementation(TestDeps.junit)
}
