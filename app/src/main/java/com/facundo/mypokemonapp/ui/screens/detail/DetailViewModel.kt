package com.facundo.mypokemonapp.ui.screens.detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundo.mypokemonapp.domain.GetPokemonUseCase
import com.facundo.mypokemonapp.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface DetailAction{
    data object FavoriteClick : DetailAction
    data object MessageShown : DetailAction
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonUseCase,
    ) : ViewModel() {

    private val _pokemonValue = MutableStateFlow<Pokemon>(Pokemon())
    var pokemonValue: StateFlow<Pokemon> = _pokemonValue

    private val _state = MutableStateFlow(UiState())
    var state: StateFlow<UiState> = _state

    /*var state by mutableStateOf(UiState())
        private set*/

    fun onCreate(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(isLoading = false, pokemon = pokemonUseCase(id))
            /*val result = pokemonUseCase(id)

            if (result.pokemonName.isNotEmpty()) {
                _pokemonValue.value = result ?: Pokemon()
                Log.i("resultTest",result.toString())
                loading.value = false

            }*/
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: Pokemon? = null,
        val message: String? = null
    )

    fun onAction(action: DetailAction){
        when(action){
            is DetailAction.FavoriteClick -> _state.value = _state.value.copy(message = "Favorite clicked")
            is DetailAction.MessageShown -> _state.value = _state.value.copy(message = null)
        }
    }
}

