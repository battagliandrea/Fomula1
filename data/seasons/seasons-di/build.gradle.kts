plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.data.seasons.di"
}

dependencies {
    api(projects.data.seasons.seasonsApi)
    api(projects.data.seasons.seasonsImpl)

    implementation(projects.core.network.networkApi)
}
