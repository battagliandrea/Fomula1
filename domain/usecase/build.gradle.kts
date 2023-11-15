plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.domain.usecase"
}

dependencies {
    implementation(projects.data.results.resultsApi)
    implementation(projects.domain.models)

    implementation(libs.javax.inject)
}
