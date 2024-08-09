plugins {
    id("pokemon.android.feature")
    id("pokemon.di.library.compose")
}

android {
    namespace = "com.facundo.mypokemonapp.ui.detail"

}

dependencies {
    implementation(project(":domain:pokemon"))


}