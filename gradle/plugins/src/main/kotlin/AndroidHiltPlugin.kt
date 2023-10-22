import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

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
        with(pluginManager) {
            apply("dagger.hilt.android.plugin")
            apply("org.jetbrains.kotlin.kapt")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            "implementation"(libs.findLibrary("dagger.hilt.android").get())
            "kapt"(libs.findLibrary("dagger.hilt.compiler").get())
        }
    }
}