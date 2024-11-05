package mypokemonapp.ui.home

import app.cash.turbine.test
import com.facundo.mypokemonapp.domain.pokemon.data.PokeLocalDataSource
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRemoteDataSource
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetListPokemonUseCase
import com.facundo.unit.domain.pokemon.samplePokemons
import com.facundo.mypokemonapp.domain.region.data.*
import com.facundo.mypokemonapp.ui.common.*
import com.facundo.unit.data.buildPokemonRepositoryWith
import com.facundo.unit.testrules.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTests {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = samplePokemons(1, 2)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Pokemon>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest{
        val localData = samplePokemons(1,2)
        val vm = buildViewModelWith(localData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }
}

private fun buildViewModelWith(
    localData: List<Pokemon> = emptyList(),
    remoteData: List<Pokemon> = emptyList()
): HomeViewModel {
    val fetchPokemonUseCase = GetListPokemonUseCase(buildPokemonRepositoryWith(localData, remoteData))
    return HomeViewModel(fetchPokemonUseCase)
}