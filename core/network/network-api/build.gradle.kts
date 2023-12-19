plugins {
    id("f1.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "it.battagliandrea.formula1.core.network.api"

    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://ergast.com/api/f1/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://ergast.com/api/f1/\"")
        }
    }
}

dependencies {
    api(projects.core.resource)

    api(libs.kotlinx.serialization)
    api(libs.okhttp)
    api(libs.okhttp.logging)
    api(libs.retrofit)
    api(libs.retrofit.serialization)
    api(libs.sandwich)
}
