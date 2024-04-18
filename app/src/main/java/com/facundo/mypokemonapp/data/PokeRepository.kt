package com.facundo.mypokemonapp.data

import com.facundo.mypokemonapp.data.network.PokeService
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.toDomain
import javax.inject.Inject

class PokeRepository @Inject constructor( private val api: PokeService) {

    suspend fun getPokemonList(): List<Pokemon> {
        val response = api.getAllPokemon()
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonDetail(id: Int): Pokemon {
        val response = api.getPokemon(id)

        return response?.toDomain() ?: Pokemon()
    }


}