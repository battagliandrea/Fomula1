plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.domain.usecase"
}

dependencies {
    api(projects.core.resource)
    api(projects.domain.models)

    implementation(projects.data.results.resultsApi)

    implementation(libs.javax.inject)

    testImplementation(projects.core.test.testAndroid)
    testImplementation(projects.domain.test)
}
