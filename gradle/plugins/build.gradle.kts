plugins {
    `kotlin-dsl`
}

group = "it.battagliandrea.gradle.plugins"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}
