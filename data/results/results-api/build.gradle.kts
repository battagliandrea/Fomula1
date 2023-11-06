plugins {
    id("f1.jvm.library")
}

dependencies {
    implementation(projects.domain.models)

    implementation(libs.kotlinx.coroutines.core.jvm)
}
