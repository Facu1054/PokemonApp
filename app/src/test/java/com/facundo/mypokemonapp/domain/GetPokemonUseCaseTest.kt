package com.facundo.mypokemonapp.domain

import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetPokemonUseCase
import com.facundo.pokemon.data.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest {
    @MockK
    private lateinit var repository: PokeRepository
    lateinit var getListPokemonUseCase: GetPokemonUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getListPokemonUseCase = GetPokemonUseCase(repository)
    }

    @Test
    fun `when invoke is called, then call repository to get info from specific pokemon`() = runBlocking {
        val expectedPokemon = com.facundo.pokemon.data.model.Pokemon(
            1,
            "bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        //Given
        coEvery { repository.getPokemonDetail(1) } returns expectedPokemon

        //When
        val useCaseResponse = getListPokemonUseCase(1)
        //Then
        assertEquals(expectedPokemon, useCaseResponse)
    }

    @Test
    fun `when invoke is called, then call repository and get emptylist`() = runBlocking {
        //Given
        val expectedPokemon = com.facundo.pokemon.data.model.Pokemon(
            1,
            "bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        //Given
        coEvery { repository.getPokemonDetail(1) } returns com.facundo.pokemon.data.model.Pokemon()

        //When
        val useCaseResponse = getListPokemonUseCase(1)

        //Then
        coVerify(exactly = 1) { repository.getPokemonDetail(1) }
        assertEquals(com.facundo.pokemon.data.model.Pokemon(), useCaseResponse)
    }

    @Test
    fun `when invoke is called, then call repository atLeast one time`() = runBlocking {
        //Given
        val expectedPokemon = com.facundo.pokemon.data.model.Pokemon(
            1,
            "bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        //Given
        coEvery { repository.getPokemonDetail(1) } returns com.facundo.pokemon.data.model.Pokemon()

        //When
        val useCaseResponse = getListPokemonUseCase(1)

        //Then
        coVerify(atLeast = 1) { repository.getPokemonDetail(1) }
    }
}