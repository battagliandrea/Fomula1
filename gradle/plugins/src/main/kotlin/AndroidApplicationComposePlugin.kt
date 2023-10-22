import com.android.build.api.dsl.ApplicationExtension
import it.battagliandrea.gradle.plugins.conventions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Gradle plugin class for configuring an Android application project with Jetpack Compose.
 *
 * This plugin extends the functionality of the Android Gradle Plugin to set up and configure
 * a project for Android application development with Jetpack Compose UI framework.
 *
 * After applying the plugin, it configures the project for Android application development
 * with Jetpack Compose.
 */
class AndroidApplicationComposePlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val extension = extensions.getByType<ApplicationExtension>()
        configureAndroidCompose(extension)
    }
}
