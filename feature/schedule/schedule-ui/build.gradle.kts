plugins {
    id("f1.android.library.compose")
    id("f1.android.hilt")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.schdeule.ui"
}

dependencies {
    implementation(projects.core.ui.compose)
    implementation(projects.core.ui.mvi)
    implementation(projects.core.ui.navigation)
    implementation(projects.domain.usecase)
}
