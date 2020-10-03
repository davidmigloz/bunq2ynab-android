plugins {
    applyLibDefaultPlugins()
}

android {
    applyLibDefaultAndroidConfig()
    applyLibDefaultBuildTypesConfig()
    buildTypes {
        getByName("debug") {
            buildConfigField("BASE_URL_BUNQ", "")
        }
        getByName("release") {
            buildConfigField("BASE_URL_BUNQ", "")
        }
    }
}

dependencies {
    applyLibDefaultDependencies()
    implementation(project(":domain"))
}
