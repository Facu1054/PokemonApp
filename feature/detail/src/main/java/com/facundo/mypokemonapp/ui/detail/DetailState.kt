package com.facundo.mypokemonapp.ui.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.ui.common.Result


@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Pokemon>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
) {

        val pokemon: Pokemon?
        get() = (state as? Result.Success)?.data

        val topBarTitle: String
        get() = pokemon?.pokemonName ?: ""


    @Composable
    fun ShowMessageEffect(message: String, onMessageShown: () -> Unit){
        LaunchedEffect(message) {
            message?.let { message ->
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(message)
                onMessageShown()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Pokemon>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember{SnackbarHostState()}
) = remember(state) { DetailState(state,scrollBehavior, snackbarHostState) }

