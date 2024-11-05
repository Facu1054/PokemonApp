package com.facundo.unit.data

import com.facundo.mypokemonapp.domain.pokemon.data.PokeLocalDataSource
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRemoteDataSource
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.domain.region.data.DEFAULT_REGION
import com.facundo.mypokemonapp.domain.region.data.RegionDataSource
import com.facundo.mypokemonapp.domain.region.data.RegionRepository
import com.facundo.unit.domain.pokemon.samplePokemons
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildPokemonRepositoryWith(
    localData: List<Pokemon> = emptyList(),
    remoteData: List<Pokemon> = emptyList()
): PokeRepository {
    val regionRepository = RegionRepository(FakeRegionSource())
    val localDataSource = FakeLocalDataSource().apply { inMemoryMovies.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { pokemon = remoteData }

    return PokeRepository(regionRepository, localDataSource, remoteDataSource)
}

class FakeRegionSource : RegionDataSource {
    var region: String = DEFAULT_REGION

    override suspend fun findLastRegion(): String = region
}

class FakeLocalDataSource : PokeLocalDataSource {
    val inMemoryMovies = MutableStateFlow<List<Pokemon>>(emptyList())

    override val pokes: Flow<List<Pokemon>> = inMemoryMovies

    override fun findPokemonById(id: Int): Flow<Pokemon?> =
        inMemoryMovies.map { it.firstOrNull() { pokemon -> pokemon.id == id } }

    override suspend fun savePokemons(pokemon: List<Pokemon>) {
        inMemoryMovies.value = pokemon
    }

}

class FakeRemoteDataSource : PokeRemoteDataSource {
    var pokemon = samplePokemons(1, 2, 3, 4)

    override suspend fun getAllPokemon(region: Int): List<Pokemon> = pokemon

    override suspend fun getPokemon(id: Int): Pokemon = pokemon.first() { it.id == id }

}