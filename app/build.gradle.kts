plugins {
    applyAppPlugins()
}

android {
    kotlinOptions {
        applyKotlinConfig()
    }
    applyAppAndroidConfig()
    applyAppBuildTypesConfig()
}

dependencies {
    applyAppDependencies()
    // External dependencies required to create the DI graph
    implementation(AppDeps.okHttp)
    implementation(AppDeps.retrofitConverterMoshi)
    implementation(AppDeps.moshi)
}
