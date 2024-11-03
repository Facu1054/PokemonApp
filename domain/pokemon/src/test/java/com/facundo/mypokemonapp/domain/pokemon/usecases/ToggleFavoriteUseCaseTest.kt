package com.facundo.mypokemonapp.domain.pokemon.usecases

import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.unit.domain.pokemon.samplePokemon
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest{
    @Test
    fun `Invoke calls repository`() = runBlocking {
        val pokemon = samplePokemon(1)
        val repository = mock<PokeRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(pokemon)

        verify(repository).toggleFavorite(pokemon)
    }
}