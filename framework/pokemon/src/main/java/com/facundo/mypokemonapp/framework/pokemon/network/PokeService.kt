package com.facundo.mypokemonapp.framework.pokemon.network

import com.facundo.mypokemonapp.framework.pokemon.network.model.Result
import com.facundo.mypokemonapp.framework.pokemon.network.model.generations.GenerationPokemonData
import com.facundo.mypokemonapp.framework.pokemon.network.model.generations.PokemonSpecy
import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.PokemonInfoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject



open class PokeService @Inject constructor(
    private val api: PokeApiClient
) {


    val listPokemon: Flow<List<PokemonSpecy>> = flow { emit(getAllPokemon()) }
    //val pokemon: Flow<PokemonInfoDTO> = flow { getPokemon(1)?.let { emit(it) } }

    /*suspend fun getAllPokemon(): List<Result> {
        return withContext(Dispatchers.IO){
            val response = api.getAllPokemon(4)
            response.body()?.results ?: emptyList()
        }

    }*/

    suspend fun getAllPokemon(): List<PokemonSpecy> {
        return withContext(Dispatchers.IO){
            val response = api.getAllPokemon(1)
            //val generations = response.body()?.main_region?.name ?: ""
            /*if(response.body() != null){
                response.body()?.pokemon_species?.forEach {
                    it.generation = response.body()?.main_region?.name ?: ""
                }
            }*/

            response.body()?.pokemon_species ?: emptyList()
        }

    }

    suspend fun getPokemon(id:Int): PokemonInfoDTO? {
        return withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }

    }



}

