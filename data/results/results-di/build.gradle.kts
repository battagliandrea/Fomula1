plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.data.results.di"
}

dependencies {
    api(projects.data.results.resultsApi)
    api(projects.data.results.resultsImpl)
}