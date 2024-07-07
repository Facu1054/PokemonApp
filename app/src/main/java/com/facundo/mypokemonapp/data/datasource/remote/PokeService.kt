package com.facundo.mypokemonapp.data.datasource.remote

import com.facundo.mypokemonapp.data.datasource.remote.PokeApiClient
import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject



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

