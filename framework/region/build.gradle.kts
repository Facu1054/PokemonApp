plugins {
    id("pokemon.android.library")
    id("pokemon.di.library")
}

android {
    namespace = "com.facundo.pokemon.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}