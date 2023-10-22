import com.android.build.gradle.LibraryExtension
import it.battagliandrea.gradle.plugins.conventions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Gradle plugin class for configuring an Android library project.
 *
 * This plugin extends the functionality of the Android Gradle Plugin to set up and configure
 * a project for Android library development.
 *
 * After applying the plugin, it configures the project for Android library development.
 */
class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
        }
    }
}