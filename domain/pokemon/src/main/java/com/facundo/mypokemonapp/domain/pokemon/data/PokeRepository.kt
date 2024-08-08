package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//class PokeRepository @Inject constructor( private val api: PokeService) {
open class PokeRepository @Inject constructor(
    private val localDataSource: PokeLocalDataSource,
    private val remoteDataSource: PokeRemoteDataSource
) {


    val pokemons: Flow<List<Pokemon>> = localDataSource.pokes.onEach { localPokes ->
        if (localPokes.isEmpty()) {
            val remotePokes = remoteDataSource.getAllPokemon()
            localDataSource.savePokemons(remotePokes)
            //localDataSource.insertPokemonWithAbilities(remotePokes)
        }
    }

    fun getPokemonDetail(id: Int): Flow<Pokemon> =
        localDataSource.findPokemonById(id).onEach {
            //println("entro dfe" + it)
            if (it!!.abilities.isBlank()) {
                val remotePokemon = remoteDataSource.getPokemon(id)
                localDataSource.savePokemons(listOf(remotePokemon))
            }
            /*val remotePokemon = remoteDataSource.getPokemon(id)
            localDataSource.savePokemons(listOf(remotePokemon))*/
        }.filterNotNull()

    suspend fun toggleFavorite(pokemon: Pokemon) {
        localDataSource.savePokemons(listOf(pokemon.copy(isFavorite = !pokemon.isFavorite)))
    }


}