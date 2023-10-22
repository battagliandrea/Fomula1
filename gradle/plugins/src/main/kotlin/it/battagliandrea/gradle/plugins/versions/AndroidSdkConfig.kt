package it.battagliandrea.gradle.plugins.versions
/**
 * Object containing Android SDK configuration constants for a project.
 *
 * - `compileSdk`: The Android SDK version used for compiling the project.
 * - `targetSdk`: The Android SDK version targeted by the application.
 * - `minSdk`: The minimum Android SDK version required for the application.
 */
object AndroidSdkConfig {
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24
}