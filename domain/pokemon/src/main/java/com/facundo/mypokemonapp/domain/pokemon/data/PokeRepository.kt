package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.domain.region.data.RegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//class PokeRepository @Inject constructor( private val api: PokeService) {
open class PokeRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val localDataSource: PokeLocalDataSource,
    private val remoteDataSource: PokeRemoteDataSource
) {


    val pokemons: Flow<List<Pokemon>>
        get() = localDataSource.pokes.onEach { localPokes ->
        if (localPokes.isEmpty()) {
            val region = getPokemonRegion(regionRepository.findLastRegion())
            val remotePokes = remoteDataSource.getAllPokemon(region)
            localDataSource.savePokemons(remotePokes)
            //localDataSource.insertPokemonWithAbilities(remotePokes)
        }
    }

    private fun getPokemonRegion(region:String): Int{
        /*return when(region){
            "US" -> 5
            "MX" -> 2
            "ES" -> 3
            else -> 1
        }*/
        return if (region != "US") (2..8).random() else 1
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