import it.battagliandrea.gradle.plugins.conventions.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin class for configuring Kotlin settings in a JVM-based project.
 *
 * This plugin extends the functionality of the Kotlin Gradle Plugin to set up and configure
 * Kotlin settings specifically for a JVM-based project.
 *
 * After applying the plugin, it configures the project for Kotlin development in a JVM-based environment.
 */
class KotlinJvmPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.jvm")
        }

        configureKotlinJvm()
    }
}