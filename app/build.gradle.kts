
plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    //id("pokemon.android.library")

    id("pokemon.android.application")
    id("pokemon.android.application.compose")
    id("pokemon.di.library.compose")
}

android {
    namespace = "com.facundo.mypokemonapp"

    defaultConfig {
        applicationId = "com.facundo.mypokemonapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":domain:pokemon"))
    implementation(project(":domain:region"))

    implementation(project(":framework:core"))
    implementation(project(":framework:pokemon"))
    implementation(project(":framework:region"))

    implementation(project(":feature:home"))
    implementation(project(":feature:common"))
    implementation(project(":feature:detail"))



    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Retrofit
    //implementation(libs.retrofit)
    //implementation(libs.retrofit.converter.gson)


    //Hilt
    //implementation(libs.hilt.android)
    //implementation(libs.androidx.hilt.navigation.compose)
    //kapt(libs.hilt.compiler)
    /*implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")*/


}