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
    implementation(AppDeps.okHttp)
    implementation(AppDeps.okHttpLoggingInterceptor)
    implementation(AppDeps.retrofit)
    implementation(AppDeps.retrofitConverterMoshi)
    implementation(AppDeps.moshi)
    kapt(AppDeps.moshiKotlinCodeGen)
}
