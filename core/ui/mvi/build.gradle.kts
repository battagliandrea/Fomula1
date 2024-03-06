plugins {
    id("f1.android.library")
}

android {
    namespace = "it.battagliandrea.formula1.core.ui.mvi"
}

dependencies {
    api(libs.bundles.compose.lifecycle)

    implementation(libs.kotlinx.coroutines.android)
}
