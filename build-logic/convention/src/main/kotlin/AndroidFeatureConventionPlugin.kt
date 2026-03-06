import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "gigboard.android.library")
            apply(plugin = "gigboard.android.compose")
            apply(plugin = "gigboard.android.hilt")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:domain"))

                add("implementation", libs.findLibrary("navigation-compose").get())
                add("implementation", libs.findLibrary("hilt-navigation").get())
                add("implementation", libs.findLibrary("lifecycle-runtime").get())
                add("implementation", libs.findLibrary("lifecycle-viewmodel").get())
            }
        }
    }
}
