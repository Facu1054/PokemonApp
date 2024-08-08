package com.facundo.mypokemonapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundo.mypokemonapp.Result
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetListPokemonUseCase
import com.facundo.mypokemonapp.stateAsResultIn
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

//@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonUseCase: GetListPokemonUseCase,


    ) : ViewModel() {

    /*private val _pokemonValue = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())
    var pokemonValue: StateFlow<MutableList<Pokemon>> = _pokemonValue*/

    /*var state by mutableStateOf(UiState())
        private set*/

    //private val _state = MutableStateFlow(UiState())
    private val uiReady = MutableStateFlow(false)
    var state: StateFlow<Result<List<Pokemon>>> = uiReady
        .filter { it }
        .flatMapLatest { pokemonUseCase() }
        .stateAsResultIn(viewModelScope)

    init {
        uiReady.value = true
        /*viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(isLoading = false, pokemon = pokemonUseCase())

            /*if (!result.isNullOrEmpty()) {
                _pokemonValue.value = result.toMutableList() ?: mutableListOf()
                Log.i("resultTest",result.toString())
                loading.value = false

            }*/
        }*/
    }


    fun onUiReady() {
        uiReady.value = true
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: List<Pokemon> = emptyList()
    )
}