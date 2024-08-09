plugins {
    id("pokemon.android.library.compose")

}

android {
    namespace = "com.facundo.mypokemonapp.ui.common"
}

dependencies {

    implementation(libs.androidx.activity.compose)

}