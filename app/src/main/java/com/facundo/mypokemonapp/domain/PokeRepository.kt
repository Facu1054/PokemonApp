package com.facundo.mypokemonapp.domain

import com.facundo.mypokemonapp.data.network.PokeService
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.toDomain
import javax.inject.Inject

class PokeRepository @Inject constructor( private val api: PokeService) {

    suspend fun getPokemonList(): List<Pokemon> {
        val response = api.getAllPokemon()
        return response.map { it.toDomain() }
    }


}