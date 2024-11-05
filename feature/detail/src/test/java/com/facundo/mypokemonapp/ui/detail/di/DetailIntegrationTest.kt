package com.facundo.mypokemonapp.ui.detail.di

import app.cash.turbine.test
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetPokemonUseCase
import com.facundo.mypokemonapp.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.facundo.mypokemonapp.ui.common.*
import com.facundo.mypokemonapp.ui.detail.DetailViewModel
import com.facundo.unit.data.buildPokemonRepositoryWith
import com.facundo.unit.domain.pokemon.samplePokemon
import com.facundo.unit.domain.pokemon.samplePokemons
import com.facundo.unit.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel



    @Before
    fun setUp() {
        val moviesRepository = buildPokemonRepositoryWith(localData = samplePokemons(1,2,3,4))
        vm = DetailViewModel(
            2,
            GetPokemonUseCase(moviesRepository),
            ToggleFavoriteUseCase(moviesRepository)
        )
    }

    @Test
    fun `UI is updated with the pokemon on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(samplePokemon(2)),awaitItem())
        }
    }

    @Test
    fun `Favorite is updates in local data sources`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(samplePokemon(2)), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            assertEquals(Result.Success(samplePokemon(2).copy(isFavorite = true)), awaitItem())
        }
    }
}