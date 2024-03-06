plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.domain.usecase"
}

dependencies {
    api(libs.arrow.core)
    api(projects.domain.models)

    implementation(projects.data.races.racesApi)
    implementation(projects.data.seasons.seasonsApi)

    implementation(libs.javax.inject)

    testImplementation(projects.core.test.testAndroid)
    testImplementation(projects.domain.test)
}
