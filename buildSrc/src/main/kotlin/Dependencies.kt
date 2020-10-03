internal object GlobalVersions {
    const val kotlin =
        "1.4.10" // https://kotlinlang.org/docs/reference/using-gradle.html#plugin-and-versions
    const val hilt =
        "2.29.1-alpha" // https://github.com/google/dagger/releases
    const val androidXNavigation =
        "2.3.0" // https://developer.android.com/jetpack/androidx/releases/navigation
}

object GradleDeps {
    private object Versions {
        const val androidGradle =
            "4.1.0-rc03" // https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
    }

    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GlobalVersions.kotlin}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${GlobalVersions.hilt}"
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val androidXNavigationSafeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${GlobalVersions.androidXNavigation}"
}

object AppDeps {
    private object Versions {
        const val kotlinXCoroutines =
            "1.3.9" // https://github.com/Kotlin/kotlinx.coroutines/releases
        const val androidXCore =
            "1.3.1" // https://developer.android.com/jetpack/androidx/releases/core
        const val androidXAppCompat =
            "1.2.0" // https://developer.android.com/jetpack/androidx/releases/appcompat
        const val androidXFragment =
            "1.2.5" // https://developer.android.com/jetpack/androidx/releases/fragment
        const val androidXConstraintLayout =
            "2.0.0" // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val androidXLifecycle =
            "2.2.0" // https://developer.android.com/jetpack/androidx/releases/lifecycle
        const val androidXHilt =
            "1.0.0-alpha02" // https://developer.android.com/jetpack/androidx/releases/hilt
        const val materialComponents =
            "1.2.1" // https://github.com/material-components/material-components-android/releases
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${GlobalVersions.kotlin}"
    const val kotlinXCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinXCoroutines}"
    const val hilt = "com.google.dagger:hilt-android:${GlobalVersions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${GlobalVersions.hilt}"
    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.androidXAppCompat}"
    const val androidXFragment = "androidx.fragment:fragment-ktx:${Versions.androidXFragment}"
    const val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"
    const val androidXLifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidXLifecycle}"
    const val androidXLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidXLifecycle}"
    const val androidXLifecycleViewModelSaveState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidXLifecycle}"
    const val androidXLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidXLifecycle}"
    const val androidXLifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.androidXLifecycle}"
    const val androidXLifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.androidXLifecycle}"
    const val androidXHiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidXHilt}"
    const val androidXHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidXHilt}"
    const val androidXNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${GlobalVersions.androidXNavigation}"
    const val androidXNavigationUI = "androidx.navigation:navigation-ui-ktx:${GlobalVersions.androidXNavigation}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
}

object TestDeps {
    private object Versions {
        const val junit =
            "4.13" // https://junit.org/junit4
    }

    const val junit = "junit:junit:${Versions.junit}"
}
