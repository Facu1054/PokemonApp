package com.facundo.mypokemonapp.domain.pokemon.usecases

import com.facundo.unit.domain.pokemon.samplePokemons
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetListPokemonUseCaseTest{
    @Test
    fun `Invoque calls repository`() {
        //Given
        val pokemonFlow = flowOf(samplePokemons(1,2,3,4))
        val useCase = GetListPokemonUseCase(mock {
            on { pokemons } doReturn pokemonFlow
        })
        //Arrange
        val result = useCase()
        //Then
        assertEquals(pokemonFlow, result)
    }
}