@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("f1.android.application")
    id("f1.android.application.compose")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1"

    defaultConfig {
        applicationId = "it.battagliandrea.formula1"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.core.dispatcher.dispatcherDi)
    implementation(projects.core.network.networkDi)
    implementation(projects.core.ui.compose)
    implementation(projects.data.results.resultsDi)
    implementation(projects.domain.models)
    implementation(projects.feature.main.mainUi)

    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.appyx.backstack.android)
    implementation(libs.appyx.navigation)
    implementation(libs.appyx.spotlight.android)
}
