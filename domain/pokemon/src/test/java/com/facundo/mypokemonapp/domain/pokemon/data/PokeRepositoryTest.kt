package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.domain.region.data.DEFAULT_REGION
import com.facundo.mypokemonapp.domain.region.data.RegionRepository
import com.facundo.unit.domain.pokemon.samplePokemon
import com.facundo.unit.domain.pokemon.samplePokemons
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PokeRepositoryTest{
    @Mock
    lateinit var regionRepository: RegionRepository
    @Mock
    lateinit var localDataSource: PokeLocalDataSource
    @Mock
    lateinit var remoteDataSource: PokeRemoteDataSource

    private lateinit var repository: PokeRepository

    private val localPokmeon = samplePokemons(1,2)
    private val remotePokemon = samplePokemons(3,4)
    @Before
    fun setUp() {
        repository = PokeRepository(regionRepository, localDataSource, remoteDataSource)
    }

    @Test
    fun `Pokemon are taken from local data source if available`() = runBlocking {
        whenever(localDataSource.pokes).thenReturn(flowOf(localPokmeon))

        val result = repository.pokemons

        assertEquals(localPokmeon, result.first())
    }

    @Test
    fun `Pokemon are saved to local data source when its empty`(): Unit = runBlocking {
        whenever(localDataSource.pokes).thenReturn(flowOf(emptyList()))
        whenever(regionRepository.findLastRegion()).thenReturn(DEFAULT_REGION)
        whenever(remoteDataSource.getAllPokemon(1)).thenReturn(remotePokemon)

        repository.pokemons.first()

        verify(localDataSource).savePokemons(remotePokemon)
    }

    @Test
    fun `Toggling favorite updates local data source`() = runBlocking{
        val pokemon = samplePokemon(3)

        repository.toggleFavorite(pokemon)

        verify(localDataSource).savePokemons(argThat { get(0).id == 3 })
    }

    @Test
    fun `Switching favorite marks as an unfavorite pokemon`():Unit = runBlocking {
        val pokemon = samplePokemon(1).copy(isFavorite = false)
        repository.toggleFavorite(pokemon)
        verify(localDataSource).savePokemons(argThat { get(0).isFavorite })
    }

    @Test
    fun `Switching unfavorite marks as an favorite pokemon`():Unit = runBlocking {
        val movie = samplePokemon(1).copy(isFavorite = true)
        repository.toggleFavorite(movie)

        verify(localDataSource).savePokemons(argThat { !get(0).isFavorite })
    }
}
