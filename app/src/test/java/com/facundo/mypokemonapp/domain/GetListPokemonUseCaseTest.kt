package com.facundo.mypokemonapp.domain

import com.facundo.mypokemonapp.data.PokeRepository
import com.facundo.mypokemonapp.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PokeResositoryFake : PokeRepository(mockk()){
    val expectedPokemonList = listOf(
        Pokemon(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
        Pokemon(2, "ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
    )
    override suspend fun getPokemonList(): List<Pokemon> = expectedPokemonList
    override suspend fun getPokemonDetail(id: Int): Pokemon = expectedPokemonList.first { it.id == id }


}

class GetListPokemonUseCaseTest{
    @MockK
    private lateinit var repository: PokeRepository
    lateinit var getListPokemonUseCase: GetListPokemonUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getListPokemonUseCase = GetListPokemonUseCase(repository)
    }

    @Test
    fun `when invoke is called, then call repository getPokemonList`() = runBlocking {
        val expectedPokemonList = listOf(
            Pokemon(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
            Pokemon(2, "ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
        )
        //Given
        coEvery { repository.getPokemonList() } returns expectedPokemonList
                //emptyList()

        //When
        val useCaseResponse = getListPokemonUseCase()

        //Then
        coVerify(atLeast = 1) { repository.getPokemonList() }
        assertEquals(expectedPokemonList, useCaseResponse)
    }

    /*@Test
        fun `when invoke is called, then call repository getPokemonList`() {
            val expectedPokemonList = listOf(
                Pokemon(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                Pokemon(2, "ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
            )
            // Given
            val repository = mockk<PokeResositoryFake>(relaxed = true) {
                coEvery { getPokemonList() } returns expectedPokemonList
            }
            val useCase = GetListPokemonUseCase(repository)

            // When
            val pokemon = runBlocking { useCase() }

            // Then
            //coVerify {  repository.getPokemonList() }
            assertEquals(expectedPokemonList, pokemon)
        }*/

    @Test
    fun `when invoke is called, then call repository getPokemon list and get a empty list`() = runBlocking {
        //Given
        coEvery { repository.getPokemonList() } returns emptyList()

        //When
        getListPokemonUseCase()

        //Then
        coVerify(exactly = 1) { repository.getPokemonList() }
    }

}