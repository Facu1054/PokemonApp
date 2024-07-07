package com.facundo.mypokemonapp.data.datasource

import com.facundo.mypokemonapp.data.datasource.remote.PokeApiClient
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.AddAbility
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeRemoteDataSource @Inject constructor(
    private val api: PokeApiClient
) {
    open suspend fun getAllPokemon(): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO){
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }
        return responseBody.map { it.toDomainModel() }
    }

    open suspend fun getAllAbilities(id:Int): List<Ability> {
        val response = withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }
        return response?.AddAbility() ?: emptyList()
    }

    /*open suspend fun getPokemonList(): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO) {
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }
        return responseBody.map { it.toDomain() }
    }*/


    open suspend fun getPokemon(id:Int): Pokemon {
        val response = withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }
        return response?.toDomainModel() ?: Pokemon()
    }


}



