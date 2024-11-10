import com.android.build.api.dsl.ApplicationExtension
import com.facundo.mypokemonapp.configureAndroidCompose
import com.facundo.mypokemonapp.addAndroidTestDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)

            addAndroidTestDependencies()

        }
    }
}