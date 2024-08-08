package com.facundo.mypokemonapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbPokemon::class], version = 4, exportSchema = false)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    //abstract fun pokeAbilityDao(): PokeAbilityDao
    //abstract fun pokemonAbilityCrossRefDao(): PokemonAbilityCrossRefDao
}