plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

val kotlinGradle =
    "1.4.10" // https://kotlinlang.org/docs/reference/using-gradle.html#plugin-and-versions
val hiltGradle =
    "2.29.1-alpha" // https://github.com/google/dagger/releases
val androidGradle =
    "4.1.0-rc03" // https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
val androidXNavigation =
    "2.3.0" // https://developer.android.com/jetpack/androidx/releases/navigation

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradle")
    implementation("com.google.dagger:hilt-android-gradle-plugin:$hiltGradle")
    implementation("com.android.tools.build:gradle:$androidGradle")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$androidXNavigation")
}