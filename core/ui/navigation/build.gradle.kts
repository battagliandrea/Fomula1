plugins {
    id("f1.android.library.compose")
}

android {
    namespace = "it.battagliandrea.formula1.core.ui.navigation"
}

dependencies {
    api(projects.core.ui.activity)

    api(libs.bundles.compose.navigation)
}
