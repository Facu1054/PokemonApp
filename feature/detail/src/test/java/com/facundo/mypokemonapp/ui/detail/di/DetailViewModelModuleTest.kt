package com.facundo.mypokemonapp.ui.detail.di

import app.cash.turbine.test
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetListPokemonUseCase
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetPokemonUseCase
import com.facundo.mypokemonapp.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.facundo.mypokemonapp.ui.common.*
import com.facundo.mypokemonapp.ui.detail.DetailViewModel
import com.facundo.unit.domain.pokemon.samplePokemon
import com.facundo.unit.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelModuleTest{
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var pokemonUseCase: GetPokemonUseCase

    @Mock
    lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var vm: DetailViewModel

    private val pokemon = samplePokemon(2)

    @Before
    fun setUp(){
        whenever(pokemonUseCase(2)).thenReturn(flowOf(pokemon))
        vm = DetailViewModel(2, pokemonUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the pokemon on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite action calls the corresponding use case`() = runTest(coroutinesTestRule.testDispatcher) {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            verify(toggleFavoriteUseCase).invoke(any())
        }
    }

}