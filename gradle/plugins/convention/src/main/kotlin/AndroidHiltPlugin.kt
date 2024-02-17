import it.battagliandrea.gradle.plugins.conventions.configureHilt
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin class for configuring Hilt dependency injection in an Android project.
 *
 * This plugin extends the functionality of the Android Gradle Plugin to set up and configure
 * Hilt for dependency injection in an Android project.
 *
 * After applying the plugin, it configures Hilt for dependency injection in the Android project.
 */
class AndroidHiltPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply {
            plugin("dagger.hilt.android.plugin")
            plugin("kotlin-kapt")
        }

        configureHilt()
    }
}
