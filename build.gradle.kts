buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath (GradleDeps.kotlinGradle)
        classpath (GradleDeps.hiltGradle)
        classpath (GradleDeps.androidGradle)
        classpath (GradleDeps.androidXNavigationSafeArgsGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}
