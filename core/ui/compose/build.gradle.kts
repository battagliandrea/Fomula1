plugins {
    id("f1.android.library")
    id("f1.android.library.compose")
}

android {
    namespace = "it.battagliandrea.formula1.core.ui.compose"
}

dependencies {
    api(libs.bundles.compose)
}
