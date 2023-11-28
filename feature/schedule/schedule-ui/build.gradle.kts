plugins {
    id("f1.android.library")
    id("f1.android.library.compose")
    alias(libs.plugins.kotlin.percelize)
}

android {
    namespace = "it.battagliandrea.formula1.feature.schdeule.ui"
}

dependencies {
    implementation(projects.domain.usecase)

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.appyx.backstack.android)
    implementation(libs.appyx.navigation)
    implementation(libs.appyx.spotlight.android)
}
