package com.facundo.mypokemonapp.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.facundo.mypokemonapp.framework.pokemon.database.DbPokemon
import com.facundo.mypokemonapp.framework.pokemon.database.PokeDao

@Database(entities = [DbPokemon::class], version = 4, exportSchema = false)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    //abstract fun pokeAbilityDao(): PokeAbilityDao
    //abstract fun pokemonAbilityCrossRefDao(): PokemonAbilityCrossRefDao
}