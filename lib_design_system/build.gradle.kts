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
    implementation(project(":lib_utils"))
    implementation(AppDeps.androidXCore)
    implementation(AppDeps.androidXAppCompat)
    implementation(AppDeps.androidXFragment)
    implementation(AppDeps.androidXConstraintLayout)
    implementation(AppDeps.androidXLifecycleCommon)
    implementation(AppDeps.androidXLifecycleLiveData)
    implementation(AppDeps.materialComponents)
}
