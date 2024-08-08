plugins {
    id("pokemon.jvm.library")
    id("pokemon.di.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}