plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.core.network.di"

    buildFeatures.buildConfig = true
}

dependencies {
    api(projects.core.network.networkApi)

    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
}
