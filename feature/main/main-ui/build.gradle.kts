plugins {
    id("f1.android.library")
    id("f1.android.library.compose")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.main"
}

dependencies {
    implementation(projects.core.ui.compose)
    implementation(projects.domain.usecase)
    implementation(projects.feature.results.resultsUi)
    implementation(projects.feature.schedule.scheduleUi)
    implementation(projects.feature.standings.standingsUi)

    implementation(libs.appyx.backstack.android)
    implementation(libs.appyx.navigation)
    implementation(libs.appyx.spotlight.android)
}
