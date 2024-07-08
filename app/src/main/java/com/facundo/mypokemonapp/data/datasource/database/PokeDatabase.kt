package com.facundo.mypokemonapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon

@Database(entities = [Pokemon::class], version = 3, exportSchema = false)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    //abstract fun pokeAbilityDao(): PokeAbilityDao
    //abstract fun pokemonAbilityCrossRefDao(): PokemonAbilityCrossRefDao
}