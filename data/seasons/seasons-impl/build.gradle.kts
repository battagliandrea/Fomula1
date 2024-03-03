plugins {
    id("f1.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "it.battagliandrea.formula1.data.seasons.impl"
}

dependencies {
    implementation(projects.core.dispatcher.dispatcherApi)
    implementation(projects.core.network.networkApi)
    implementation(projects.data.core)
    implementation(projects.data.seasons.seasonsApi)

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(projects.core.test.testAndroid)
    testImplementation(projects.core.network.networkTest)
}
