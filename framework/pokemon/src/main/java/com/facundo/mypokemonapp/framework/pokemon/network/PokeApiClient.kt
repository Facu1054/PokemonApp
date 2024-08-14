package com.facundo.mypokemonapp.framework.pokemon.network


import com.facundo.mypokemonapp.framework.pokemon.network.model.PokeDTO
import com.facundo.mypokemonapp.framework.pokemon.network.model.generations.GenerationPokemonData
import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.PokemonInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiClient{

    @GET("/api/v2/pokemon")
    suspend fun getAllPokemon(
        @Query("limit") limit: Int = 50
    ): Response<PokeDTO>

    @GET("/api/v2/generation/{id}")
    suspend fun getAllPokemonRegion(
        @Path("id") id: Int,
        //@Query("limit") limit: Int = 50
    ): Response<GenerationPokemonData>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int,
    ): Response<PokemonInfoDTO>

    @GET("abilities")
    suspend fun getAbilities(): Response<PokemonInfoDTO>

    /*

     @Query("ts") ts: String = Constant.ts,
        @Query("apikey") apiKey: String = Constant.API_KEY,
        @Query("hash") hash: String = Constant.hash(),
        @Query("limit") limit: String = Constant.LIMIT,
        @Query("offset") offset: String,
     */



}