package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon

interface PokeRemoteDataSource {
    suspend fun getAllPokemon(): List<Pokemon>

    suspend fun getPokemon(id: Int): Pokemon
}