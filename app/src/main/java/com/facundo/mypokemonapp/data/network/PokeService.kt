package com.facundo.mypokemonapp.data.network

import com.facundo.mypokemonapp.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeService @Inject constructor(
    private val api: PokeApiClient
) {
    //val retrofitCreate = retrofit.create(ComicsApiClient::class.java)

    suspend fun getAllPokemon(): List<Result> {
        return withContext(Dispatchers.IO){
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }

    }



}

