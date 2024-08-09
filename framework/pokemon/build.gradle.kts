plugins {
    id("pokemon.android.library")
    id("pokemon.android.room")
    id("pokemon.jvm.retrofit")
    id("pokemon.di.library")
}

android {
    namespace = "com.facundo.mypokemonapp.framework.pokemon"

}

dependencies {

    implementation(project(":domain:pokemon"))
    //implementation(project(":domain:region"))
}