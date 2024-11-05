package mypokemonapp.ui.home

import app.cash.turbine.test
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetListPokemonUseCase
import com.facundo.mypokemonapp.ui.common.Result
import com.facundo.unit.domain.pokemon.samplePokemons
import com.facundo.unit.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var pokemonUseCase: GetListPokemonUseCase

    private lateinit var vm: HomeViewModel

    @Before
    fun setUp(){
        vm = HomeViewModel(pokemonUseCase)
    }

    @Test
    fun `Pokemon are not requested if UI is not ready`() = runTest{
        vm.state.first()
        runCurrent()

        verify(pokemonUseCase, times(0)).invoke()
    }

    @Test
    fun `Pokemon are requested if UI is ready`() = runTest{
        val pokemon = samplePokemons(1,2,3)
        whenever(pokemonUseCase()).thenReturn(flowOf(pokemon))

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())
        }
    }

    @Test
    fun `Error is propagated when request fails`() = runTest{
        val error = RuntimeException("Boom!")
        whenever(pokemonUseCase()).thenThrow(error)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessaga = (awaitItem() as Result.Error).exception.message
            assertEquals("Boom!", exceptionMessaga)
        }
    }
}