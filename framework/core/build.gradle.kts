plugins {
    id("pokemon.android.library")
    id("pokemon.android.room")
    id("pokemon.jvm.retrofit")
    id("pokemon.di.library")
}

android {
    namespace = "com.facundo.mypokemonapp.framework.core"

}

dependencies {

   implementation(project(":framework:pokemon"))
   //implementation(project(":domain:region"))
}