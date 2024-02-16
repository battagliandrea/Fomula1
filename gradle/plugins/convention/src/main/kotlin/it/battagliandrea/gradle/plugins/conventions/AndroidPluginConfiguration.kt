package it.battagliandrea.gradle.plugins.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

private const val COMPILE_SDK = 34
private const val TARGET_DSK = 34
private const val MIN_SDK = 26

internal fun Project.configureAndroidApplication(){
    project.extensions.getByType(ApplicationExtension::class.java).apply {
        compileSdk = COMPILE_SDK
        defaultConfig {
            minSdk = MIN_SDK
            targetSdk = TARGET_DSK
        }
        buildFeatures {
            compose = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        composeOptions {
            kotlinCompilerExtensionVersion = versionCatalog().findVersion("compose.compiler").get().toString()
        }
        packaging {
            resources {
                excludes += "/META-INF/*"
            }
        }
        dependencies {
            "implementation"(platform (versionCatalog().findLibrary("androidx.compose.bom").get()))
        }
    }
}

internal fun Project.configureAndroidLibrary(){
    project.extensions.getByType(LibraryExtension::class.java).apply {
        compileSdk = COMPILE_SDK
        defaultConfig {
            minSdk = MIN_SDK
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        packaging {
            resources {
                excludes += "/META-INF/*"
            }
        }
    }
}

internal fun Project.configureAndroidLibraryWithCompose(){
    project.extensions.getByType(LibraryExtension::class.java).apply {
        compileSdk = COMPILE_SDK
        defaultConfig {
            minSdk = MIN_SDK
        }
        buildFeatures {
            compose = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        packaging {
            resources {
                excludes += "/META-INF/*"
            }
        }
        composeOptions {
            kotlinCompilerExtensionVersion = versionCatalog().findVersion("compose.compiler").get().toString()
        }
        dependencies {
            "implementation"(platform (versionCatalog().findLibrary("androidx.compose.bom").get()))
        }
    }
}

internal fun Project.configureHilt(){
    dependencies {
        "implementation"(versionCatalog().findLibrary("dagger.hilt.android").get())
        "kapt"(versionCatalog().findLibrary("dagger.hilt.compiler").get())
    }
}