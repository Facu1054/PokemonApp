package com.facundo.mypokemonapp.data

import com.facundo.mypokemonapp.data.network.PokeApiClient
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

//class PokeRepository @Inject constructor( private val api: PokeService) {
open class PokeRepository @Inject constructor( private val api: PokeApiClient) {
    //val apiResponse: Flow<List<Pokemon>>
    //val listPokemon = api.listPokemon
    //val pokemon = api.pokemon
    val listPokemon = flow { emit(getPokemonList()) }



    open suspend fun getPokemonList(): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO){
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }
        return responseBody.map { it.toDomain() }
    }

    /*suspend fun getPokemonDetail(id: Int): Pokemon {
        val response = api.getPokemon(id)

        return response?.toDomain() ?: Pokemon()
    }*/
    open suspend fun getPokemonDetail(id: Int): Pokemon {
        val response = withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }
        return response?.toDomain() ?: Pokemon()
    }

}