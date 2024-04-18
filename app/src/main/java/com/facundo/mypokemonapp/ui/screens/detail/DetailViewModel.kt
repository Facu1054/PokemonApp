package com.facundo.mypokemonapp.ui.screens.detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonUseCase,
    ) : ViewModel() {

    private val _pokemonValue = MutableStateFlow<Pokemon>(Pokemon())
    var pokemonValue: StateFlow<Pokemon> = _pokemonValue

    var state by mutableStateOf(UiState())
        private set

    fun onCreate(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            state = UiState(isLoading = true)
            state = UiState(isLoading = false, pokemon = pokemonUseCase(id))
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
        val pokemon: Pokemon = Pokemon()
    )
}

