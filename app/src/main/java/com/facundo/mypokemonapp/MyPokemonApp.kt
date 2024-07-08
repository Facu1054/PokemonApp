package com.facundo.mypokemonapp

import android.app.Application
import androidx.room.Room
import com.facundo.mypokemonapp.data.datasource.database.PokeDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyPokemonApp: Application(){

    /*lateinit var db: PokeDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, PokeDatabase::class.java, "pokemon.db")
            .build()
    }*/
}