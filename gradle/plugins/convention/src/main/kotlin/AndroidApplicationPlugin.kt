import com.android.build.api.dsl.ApplicationExtension
import it.battagliandrea.gradle.plugins.conventions.AndroidSdk
import it.battagliandrea.gradle.plugins.conventions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Gradle plugin class for configuring an Android application project.
 *
 * This plugin extends the functionality of the Android Gradle Plugin to set up and configure
 * a project for Android application development.
 *
 * After applying the plugin, it configures the project for Android application development.
 */
class AndroidApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("f1.spotless")
        }

        extensions.configure(ApplicationExtension::class){
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = AndroidSdk.targetSdk

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }
}
