plugins {
    dependencyUpdates()
    ktlint()
    detekt()
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
