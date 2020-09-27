buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath (GradleDeps.kotlinGradle)
        classpath (GradleDeps.androidGradle)
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
