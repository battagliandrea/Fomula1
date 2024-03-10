@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("f1.android.application")
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
    implementation(projects.core.ui.navigation)
    implementation(projects.data.races.racesDi)
    implementation(projects.data.seasons.seasonsDi)
    implementation(projects.domain.models)
    implementation(projects.feature.home.homeUi)
    implementation(projects.feature.main.mainUi)
    implementation(projects.feature.results.resultsUi)
    implementation(projects.feature.schedule.scheduleUi)
    implementation(projects.feature.standings.standingsUi)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
}
