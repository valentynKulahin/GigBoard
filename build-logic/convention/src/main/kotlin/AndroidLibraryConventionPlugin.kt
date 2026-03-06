import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            configureKotlinAndroid()

            dependencies {
                add("implementation", libs.findLibrary("androidx-core-ktx").get())
            }
        }
    }
}