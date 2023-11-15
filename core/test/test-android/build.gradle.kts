plugins {
    id("f1.android.library")
}

android {
    namespace = "it.battagliandrea.formula1.core.test.android"
}

dependencies {
    api(projects.core.test.testJvm)
    api(libs.mockk.agent)
    api(libs.mockk.android)
}
