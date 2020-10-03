// Kotlin : https://kotlinlang.org/docs/reference/using-gradle.html#plugin-and-versions
const val kotlinVersion = "1.4.10"
// Hilt : https://developer.android.com/jetpack/androidx/releases/hilt
const val hiltVersion = "2.29.1-alpha"

object GradleDeps {
    object Versions {
        // https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
        const val androidGradle = "4.1.0-rc03"
    }

    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
}

object AndroidSdk {
    const val minSdkVersion = 24
    const val compileSdkVersion = 30
    const val targetSdkVersion = compileSdkVersion
}

object AppDeps {
    private object Versions {
        // https://github.com/Kotlin/kotlinx.coroutines/releases
        const val kotlinxCoroutines = "1.3.9"

        // https://developer.android.com/jetpack/androidx/releases/core
        const val androidxCore = "1.3.1"

        // https://developer.android.com/jetpack/androidx/releases/appcompat
        const val androidxAppCompat = "1.2.0"

        // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val androidxConstraintLayout = "2.0.0"

        // https://github.com/material-components/material-components-android/releases | https://mvnrepository.com/artifact/com.google.android.material/material
        const val materialComponents = "1.2.1"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
}

object TestDeps {
    private object Versions {
        // https://junit.org/junit4/
        const val junit = "4.13"
    }

    const val junit = "junit:junit:${Versions.junit}"
}