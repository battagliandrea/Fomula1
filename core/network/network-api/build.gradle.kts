plugins {
    id("f1.android.library")
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
    api(libs.okhttp)
    api(libs.okhttp.logging)
    api(libs.retrofit)
    api(libs.retrofit.serialization)
    api(libs.sandwich)
}
