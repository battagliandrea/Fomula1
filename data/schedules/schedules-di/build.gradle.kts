plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.data.schedules.di"
}

dependencies {
    api(projects.data.schedules.schedulesApi)
    api(projects.data.schedules.schedulesImpl)

    implementation(projects.core.network.networkApi)

    implementation(libs.arrow.core.retrofit)
}
