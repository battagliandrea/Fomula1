plugins {
    id("f1.android.library")
}

android {
    namespace = "it.battagliandrea.formula1.data.results.impl"
}

dependencies {
    implementation(projects.data.results.resultsApi)
}