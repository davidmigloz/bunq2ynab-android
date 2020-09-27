plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    val kotlin = "1.4.10"
    val androidGradlePlugin = "4.1.0-rc03"

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    implementation("com.android.tools.build:gradle:$androidGradlePlugin")
}