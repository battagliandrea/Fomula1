plugins {
    `kotlin-dsl`
}

group = "it.battagliandrea.gradle.plugins"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("JvmPlugin") {
            id = "f1.jvm.library"
            implementationClass = "JvmPlugin"
        }
        register("AndroidLibraryPlugin") {
            id = "f1.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("AndroidLibraryComposePlugin") {
            id = "f1.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("AndroidApplicationPlugin") {
            id = "f1.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("AndroidApplicationComposePlugin") {
            id = "f1.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
    }
}