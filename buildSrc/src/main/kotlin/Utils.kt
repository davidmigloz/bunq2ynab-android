import com.android.build.api.dsl.BuildType
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun BuildType.buildConfigField(name: String, value: Boolean) =
    buildConfigField("Boolean", name, value.toString())

fun BuildType.buildConfigField(name: String, value: String) =
    buildConfigField("String", name, "\"$value\"")

fun BuildType.buildConfigField(name: String, value: Int) =
    buildConfigField("Int", name, value.toString())

fun BuildType.buildConfigField(name: String, value: Float) =
    buildConfigField("Float", name, value.toString())

// I didn't manage to access these extensions functions that are declared
// in the package org.gradle.kotlin.dsl from the gradle-kotlin-dsl-accessors artifact
// So, I rewrite them here until I find a better way...

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency = project(
    if (configuration != null) mapOf("path" to path, "configuration" to configuration)
    else mapOf("path" to path)
) as ProjectDependency
