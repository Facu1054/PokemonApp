plugins {
    id("pokemon.android.feature")
    id("pokemon.di.library.compose")
}

android {
    namespace = "mypokemonapp.ui.home"
}

dependencies {

    implementation(project(":domain:pokemon"))
    implementation(project(":domain:region"))

}