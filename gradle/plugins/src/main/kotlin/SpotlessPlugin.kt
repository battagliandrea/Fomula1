import it.battagliandrea.gradle.plugins.conventions.configureSpotless
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class SpotlessPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.diffplug.spotless")
        }

        val ktlintVersion =
            extensions
                .getByType<VersionCatalogsExtension>()
                .named("libs")
                .findVersion("ktlint")
                .get()
                .requiredVersion

        extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("$buildDir/**/*.kt")
                ktlint(ktlintVersion).userData(mapOf("android" to "true"))
                    .editorConfigOverride(
                        mapOf(
                            "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                        )
                    )
                trimTrailingWhitespace()
                endWithNewline()
            }

            kotlinGradle {
                ktlint(ktlintVersion)
                trimTrailingWhitespace()
                endWithNewline()
            }

            yaml {
                target("**/*.yaml")
                prettier()
            }

            format("misc") {
                target("*.json", "*.md", ".gitignore", ".sh")

                trimTrailingWhitespace()
                endWithNewline()
                prettier()
            }
        }
    }
}