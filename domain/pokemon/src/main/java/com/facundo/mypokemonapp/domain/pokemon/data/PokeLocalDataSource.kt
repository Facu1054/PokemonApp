package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokeLocalDataSource {
    val pokes: Flow<List<Pokemon>>
    fun findPokemonById(id: Int): Flow<Pokemon?>

    suspend fun savePokemons(pokemon: List<Pokemon>)
}
