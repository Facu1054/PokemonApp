package com.facundo.mypokemonapp.data.datasource.database

import androidx.room.Database
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.relations.PokemonAbilityCrossRef

@Database(entities = [Pokemon::class, Ability::class, PokemonAbilityCrossRef::class], version = 2, exportSchema = false)
abstract class PokeDatabase {
    abstract fun pokeDao(): PokeDao
    abstract fun pokeAbilityDao(): PokeAbilityDao
}