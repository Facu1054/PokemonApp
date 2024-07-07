package com.facundo.mypokemonapp.data

import com.facundo.mypokemonapp.data.datasource.PokeLocalDataSource
import com.facundo.mypokemonapp.data.datasource.PokeRemoteDataSource
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

//class PokeRepository @Inject constructor( private val api: PokeService) {
open class PokeRepository @Inject constructor(
    private val localDataSource: PokeLocalDataSource,
    private val remoteDataSource: PokeRemoteDataSource
) {
    //val apiResponse: Flow<List<Pokemon>>
    //val listPokemon = api.listPokemon
    //val pokemon = api.pokemon
    //val listPokemon = flow { emit(getPokemonList()) }

    //suspend fun pokesList(): List<Pokemon> = remoteDataSource.getAllPokemon()
    val pokemons: Flow<List<Pokemon>> = localDataSource.pokes.onEach { localPokes ->
        if (localPokes.isEmpty()) {
            val remotePokes = remoteDataSource.getAllPokemon()
            localDataSource.savePokemon(remotePokes)
        }
    }

    fun getPokemonDetail(id: Int): Flow<Pokemon> =
        localDataSource.findPokemonById(id).onEach {
            if (it == null) {
                val remotePokemon = remoteDataSource.getPokemon(id)
                localDataSource.savePokemon(listOf(remotePokemon))
            }
        }.filterNotNull()

    suspend fun toggleFavorite(pokemon: Pokemon) {
        localDataSource.savePokemon(listOf(pokemon.copy(favorite = !pokemon.favorite)))

    }
}