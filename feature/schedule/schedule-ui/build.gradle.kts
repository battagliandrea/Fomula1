plugins {
    id("f1.android.library")
    id("f1.android.library.compose")
    id("f1.android.hilt")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.schdeule.ui"
}

dependencies {
    implementation(projects.core.ui.compose)
    implementation(projects.domain.usecase)
}
