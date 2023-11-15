plugins {
    id("f1.jvm.library")
}

dependencies {
    implementation(projects.core.test.testJvm)
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.mockwebserver)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.sandwich)
}
