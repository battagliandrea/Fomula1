plugins {
    id("f1.android.library.compose")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.standings.ui"
}

dependencies {
    implementation(projects.core.ui.compose)
    implementation(projects.domain.usecase)
}
