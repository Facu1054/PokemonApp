package com.facundo.mypokemonapp.framework.core

import com.facundo.mypokemonapp.framework.pokemon.network.PokeApiClient
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

internal class PokemonClient(private val apiUrl: String) {
    val instance = Retrofit.Builder()
    .baseUrl(apiUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create<PokeApiClient>()


}