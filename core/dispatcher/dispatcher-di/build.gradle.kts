plugins {
    id("f1.android.library")
    id("f1.android.hilt")
}

android {
    namespace = "it.battagliandrea.formula1.core.dispatcher.di"
}

dependencies {
    api(projects.core.dispatcher.dispatcherApi)
}
