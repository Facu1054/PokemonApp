package com.facundo.mypokemonapp.framework.pokemon.network

import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.toDomainModel
import com.facundo.mypokemonapp.framework.pokemon.network.model.toDomainModel
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRemoteDataSource
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.framework.pokemon.network.model.generations.PokemonSpecy
import com.facundo.mypokemonapp.framework.pokemon.network.model.generations.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject



internal class PokeServerDataSource @Inject constructor(
    private val api: PokeApiClient
) : PokeRemoteDataSource {
    /*override suspend fun getAllPokemon(): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO){
            val response = api.getAllPokemon()

            response.body()?.results ?: emptyList()
        }
        return responseBody.map { it.toDomainModel() }
    }*/

    override suspend fun getAllPokemon(region:Int): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO){
            val response = api.getAllPokemonRegion(region)
            val regionName = response.body()?.main_region?.name ?: ""
            val pokemonSpecies = response.body()?.pokemon_species?.map { it.copy(region = regionName) }

            pokemonSpecies ?: emptyList()
        }
        return responseBody.map { it.toDomainModel() }
    }

    /*open suspend fun getAllAbilities(id:Int): List<Ability> {
        val response = withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body()
        }
        return response?.AddAbility() ?: emptyList()
    }*/

    /*open suspend fun getPokemonList(): List<Pokemon> {
        val responseBody = withContext(Dispatchers.IO) {
            val response = api.getAllPokemon()
            response.body()?.results ?: emptyList()
        }
        return responseBody.map { it.toDomain() }
    }*/


    override suspend fun getPokemon(id:Int): Pokemon {
        val response = withContext(Dispatchers.IO){
            val response = api.getPokemon(id)
            response.body().also { println("id "+ it?.id) }
        }
        return response?.toDomainModel() ?: Pokemon(isFavorite = false)
    }


}








