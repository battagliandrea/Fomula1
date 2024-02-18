plugins {
    id("f1.android.library.compose")
    id("f1.android.hilt")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.main"
}

dependencies {
    implementation(projects.core.ui.compose)
    implementation(projects.core.ui.navigation)
    implementation(projects.domain.usecase)
    implementation(projects.feature.results.resultsUi)
    implementation(projects.feature.schedule.scheduleUi)
    implementation(projects.feature.standings.standingsUi)
}
