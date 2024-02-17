plugins {
    `kotlin-dsl`
}

group = "it.battagliandrea.gradle.plugins"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.spotless)
}

gradlePlugin {
    plugins {
        register("KotlinJvmPlugin") {
            id = "f1.jvm.library"
            implementationClass = "KotlinJvmPlugin"
        }
        register("AndroidHiltPlugin") {
            id = "f1.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
        register("AndroidLibraryPlugin") {
            id = "f1.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("AndroidLibraryUiPlugin") {
            id = "f1.android.library.compose"
            implementationClass = "AndroidLibraryUiPlugin"
        }
        register("AndroidApplicationPlugin") {
            id = "f1.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
    }
}