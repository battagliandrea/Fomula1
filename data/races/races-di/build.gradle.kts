plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.data.races.di"
}

dependencies {
    api(projects.data.races.racesApi)
    api(projects.data.races.racesImpl)

    implementation(projects.core.network.networkApi)
}
