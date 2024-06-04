package com.facundo.mypokemonapp.data.network

import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PokemonServiceInterface {
    //val listPokemon: Flow<List<Result>>
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun getPokemon(id: Int): PokemonInfoDTO?
}

open class PokeService @Inject constructor(
    private val api: PokeApiClient
) {


    val listPokemon: Flow<List<Result>> = flow { emit(getAllPokemon()) }
    //val pokemon: Flow<PokemonInfoDTO> = flow { getPokemon(1)?.let { emit(it) } }

    suspend fun getAllPokemon(): List<Result> {
        return withContext(Dispatchers.IO){
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }

    }

    suspend fun getPokemon(id:Int): PokemonInfoDTO? {
        return withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }

    }



}

