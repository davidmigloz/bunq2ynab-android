import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

object AppConfiguration {
    const val applicationId = "app.bunq2ynab"

    const val minSdkVersion = 26
    const val compileSdkVersion = 30
    const val targetSdkVersion = compileSdkVersion

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val versionName = "1.0"
    const val versionCode = 1
}

fun PluginDependenciesSpec.applyAppPlugins() {
    androidApp()
    kotlinAndroid()
    kotlinAndroidExt()
    kotlinKapt()
    hilt()
    androidXNavigationSafeArgs()
    ktlint(includeVersion = false)
    detekt(includeVersion = false)
}

fun KotlinJvmOptions.applyKotlinConfig() {
    jvmTarget = "1.8"
    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
}

fun BaseAppModuleExtension.applyAppAndroidConfig() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(AppConfiguration.compileSdkVersion)

    defaultConfig {
        minSdkVersion(AppConfiguration.minSdkVersion)
        targetSdkVersion(AppConfiguration.targetSdkVersion)
        applicationId = AppConfiguration.applicationId
        versionCode = AppConfiguration.versionCode
        versionName = AppConfiguration.versionName
        testInstrumentationRunner = AppConfiguration.testInstrumentationRunner
    }

    buildFeatures {
        dataBinding = true
    }
}

fun BaseAppModuleExtension.applyAppBuildTypesConfig() = buildTypes {
    getByName("debug") {
        isDebuggable = true
    }
    getByName("release") {
        isMinifyEnabled = false
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}

fun DependencyHandler.applyAppDependencies() {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":lib_design_system"))
    implementation(project(":lib_utils"))
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
    implementation(AppDeps.androidXBrowser)
    implementation(AppDeps.materialComponents)
    testImplementation(TestDeps.junit)
}
