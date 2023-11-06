plugins {
    id("f1.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "it.battagliandrea.formula1.data.results.impl"
}

dependencies {
    implementation(projects.core.dispatcher.dispatcherApi)
    implementation(projects.core.network.networkApi)
    implementation(projects.data.results.resultsApi)
    implementation(projects.domain.models)

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
}
