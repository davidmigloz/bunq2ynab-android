import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec = id("com.android.application")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec = id("com.android.library")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec = kotlin("android")

fun PluginDependenciesSpec.kotlinAndroidExt(): PluginDependencySpec = kotlin("android.extensions")

fun PluginDependenciesSpec.kotlinKapt(): PluginDependencySpec = kotlin("kapt")

fun PluginDependenciesSpec.hilt(): PluginDependencySpec = id("dagger.hilt.android.plugin")

fun PluginDependenciesSpec.androidXNavigationSafeArgs(): PluginDependencySpec = id("androidx.navigation.safeargs.kotlin")

// https://github.com/ben-manes/gradle-versions-plugin
fun PluginDependenciesSpec.dependencyUpdates(): PluginDependencySpec = id("com.github.ben-manes.versions").version("0.33.0")

// https://github.com/JLLeitschuh/ktlint-gradle
fun PluginDependenciesSpec.ktlint(includeVersion: Boolean = true): PluginDependencySpec = id("org.jlleitschuh.gradle.ktlint").also { if (includeVersion) it.version("9.4.0") }

// https://github.com/detekt/detekt
fun PluginDependenciesSpec.detekt(includeVersion: Boolean = true): PluginDependencySpec = id("io.gitlab.arturbosch.detekt").also { if (includeVersion) it.version("1.14.1") }