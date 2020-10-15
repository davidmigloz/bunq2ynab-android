plugins {
    applyLibDefaultPlugins()
}

android {
    kotlinOptions {
        applyKotlinConfig()
    }
    applyLibDefaultAndroidConfig()
    applyLibDefaultBuildTypesConfig()
    buildTypes {
        getByName("debug") {
            buildConfigField("BUNQ_BASE_URL", "https://public-api.sandbox.bunq.com/v1/")
            buildConfigField("BUNQ_API_KEY", "BUNQ_API_KEY")
            buildConfigField("BUNQ_OAUTH_AUTH_URL", "https://oauth.sandbox.bunq.com/auth")
            buildConfigField("BUNQ_OAUTH_TOKEN_URL", "https://api-oauth.sandbox.bunq.com/v1/token")
            buildConfigField("BUNQ_OAUTH_CLIENT_ID", "BUNQ_OAUTH_CLIENT_ID")
            buildConfigField("BUNQ_OAUTH_CLIENT_SECRET", "BUNQ_OAUTH_CLIENT_SECRET")
        }
        getByName("release") {
            buildConfigField("BASE_URL_BUNQ", "https://api.bunq.com/")
            buildConfigField("BUNQ_API_KEY", "BUNQ_API_KEY")
            buildConfigField("BUNQ_OAUTH_AUTH_URL", "https://oauth.bunq.com/auth")
            buildConfigField("BUNQ_OAUTH_TOKEN_URL", "https://api.oauth.bunq.com/v1/token")
            buildConfigField("BUNQ_OAUTH_CLIENT_ID", "BUNQ_OAUTH_CLIENT_ID")
            buildConfigField("BUNQ_OAUTH_CLIENT_SECRET", "BUNQ_OAUTH_CLIENT_SECRET")
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
    implementation(AppDeps.androidXCore)
}
