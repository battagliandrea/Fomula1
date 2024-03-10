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
    ":core:ui:activity",
    ":core:ui:compose",
    ":core:ui:mvi",
    ":core:ui:navigation",
    ":core:ui:resources",
    ":domain:models",
    ":domain:test",
    ":domain:usecase",
    ":data:core",
    ":data:races:races-api",
    ":data:races:races-di",
    ":data:races:races-impl",
    ":data:seasons:seasons-api",
    ":data:seasons:seasons-di",
    ":data:seasons:seasons-impl",
    ":feature:home:home-ui",
    ":feature:main:main-ui",
    ":feature:results:results-ui",
    ":feature:schedule:schedule-ui",
    ":feature:standings:standings-ui",
)
