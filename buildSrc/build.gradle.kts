plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    val kotlinGradle = "1.4.10"
    val hiltGradle = "2.29.1-alpha"
    val androidGradle = "4.1.0-rc03"

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradle")
    implementation("com.google.dagger:hilt-android-gradle-plugin:$hiltGradle")
    implementation("com.android.tools.build:gradle:$androidGradle")
}