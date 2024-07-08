package com.facundo.mypokemonapp.data.datasource.database

/*
import androidx.room.Insert
import androidx.room.Query
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.relations.PokemonAbilityCrossRef

interface PokemonAbilityCrossRefDao {
    @Insert
    suspend fun insert(crossRef: PokemonAbilityCrossRef)

    @Query("SELECT * FROM pokemon_ability_cross_ref WHERE pokemonId = :pokemonId")
    suspend fun getAbilitiesForPokemon(pokemonId: Long): List<Ability>

    @Query("SELECT * FROM pokemon_ability_cross_ref WHERE abilityId = :abilityId")
    suspend fun getPokemonsWithAbility(abilityId: Long): List<Pokemon>
}*/