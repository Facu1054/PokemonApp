package com.facundo.mypokemonapp.domain.pokemon.usecases

import com.facundo.unit.domain.pokemon.samplePokemon
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPokemonUseCaseTest{
    @Test
    fun `Invoke calls repository`() {
        val pokemonFlow = flowOf(samplePokemon(1))
        val useCase = GetPokemonUseCase(mock {
            on { getPokemonDetail(1) } doReturn pokemonFlow
        })
        val result = useCase(1)
        assertEquals(pokemonFlow, result)
    }
}