import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

fun Project.readProperties(filename: String): Properties {
    val propertiesFile = file(filename)
    return Properties().apply {
        load(FileInputStream(propertiesFile))
    }
}