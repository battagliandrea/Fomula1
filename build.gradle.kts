// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.jvm)  apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.android)  apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.spotless)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        val ktlintVersion = rootProject.libs.versions.ktlint.get()

        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            ktlint(ktlintVersion)
                .userData(mapOf("android" to "true"))
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

        format("kts") {
            target("**/*.kts")
            targetExclude("$buildDir/**/*.kts")
        }

        format("misc") {
            target("*.json", "*.md", ".gitignore", ".sh")

            trimTrailingWhitespace()
            endWithNewline()
            prettier()
        }
    }
}

task("addPreCommitGitHookOnBuild") {
    println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
    exec {
        commandLine("cp", "./.hooks/pre-commit", "./.git/hooks")
    }
    println("✅ Added Pre Commit Git Hook Script.")
}

true // Needed to make the Suppress annotation work for the plugins block