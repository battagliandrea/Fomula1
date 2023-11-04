plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.data.results.di"
}

dependencies {
    api(projects.data.results.resultsApi)
    api(projects.data.results.resultsImpl)

    implementation(projects.core.network.networkApi)

    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
}