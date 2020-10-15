plugins {
    applyLibDefaultPlugins()
}

android {
    kotlinOptions {
        applyKotlinConfig()
    }
    applyLibDefaultAndroidConfig()
    applyLibDefaultBuildTypesConfig()
}

dependencies {
    applyLibDefaultDependencies()
}
