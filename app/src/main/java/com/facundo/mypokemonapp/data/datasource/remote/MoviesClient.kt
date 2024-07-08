package com.facundo.mypokemonapp.data.datasource.remote
/*

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class PokemonClient() {

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<PokeApiClient>()

        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /*private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(
                chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            )
            .build()
    )*/
}*/