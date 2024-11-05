plugins {
    id("pokemon.jvm.library")
}

dependencies {
    implementation(project(":domain:pokemon"))
    implementation(project(":domain:region"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}