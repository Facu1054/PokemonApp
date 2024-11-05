plugins {
    id("pokemon.jvm.library")
    id("pokemon.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    testImplementation(project(":test:unit"))
}