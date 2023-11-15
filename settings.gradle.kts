pluginManagement {
    includeBuild("gradle/plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id){
                "dagger.hilt.android.plugin" -> useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
                "androidx.navigation.safeargs.kotlin" -> useModule("androidx.navigation:navigation-safe-args-gradle-plugin:${requested.version}")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Formula1"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":app",
    ":core:datetime",
    ":core:dispatcher:dispatcher-api",
    ":core:dispatcher:dispatcher-di",
    ":core:network:network-api",
    ":core:network:network-di",
    ":core:network:network-test",
    ":core:test:test-android",
    ":core:test:test-jvm",
    ":domain:models",
    ":domain:test",
    ":domain:usecase",
    ":data:results:results-api",
    ":data:results:results-di",
    ":data:results:results-impl"
)
