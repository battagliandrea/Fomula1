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
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.sandwich)
}
