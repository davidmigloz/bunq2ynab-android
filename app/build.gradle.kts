plugins {
    applyAppPlugins()
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
    applyAppAndroidConfig()
    applyAppBuildTypesConfig()
}

dependencies {
    applyAppDependencies()
}
