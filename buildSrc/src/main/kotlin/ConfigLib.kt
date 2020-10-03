import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.applyLibDefaultPlugins() {
    androidLibrary()
    kotlinAndroid()
    kotlinAndroidExt()
    kotlinKapt()
    hilt()
    ktlint(includeVersion = false)
    detekt(includeVersion = false)
}

fun BaseExtension.applyLibDefaultAndroidConfig() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(AppConfiguration.compileSdkVersion)

    defaultConfig {
        minSdkVersion(AppConfiguration.minSdkVersion)
        targetSdkVersion(AppConfiguration.targetSdkVersion)
        consumerProguardFiles("consumer-rules.pro")
    }
}

fun BaseExtension.applyLibDefaultBuildTypesConfig() = buildTypes {
    getByName("debug") {
        isDebuggable = true
    }
    getByName("release") {
        isMinifyEnabled = false
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}

fun DependencyHandler.applyLibDefaultDependencies() {
    implementation(AppDeps.kotlinStdLib)
    implementation(AppDeps.kotlinXCoroutines)
    implementation(AppDeps.hilt)
    kapt(AppDeps.hiltCompiler)
}