package com.facundo.mypokemonapp.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundo.mypokemonapp.domain.GetListPokemonUseCase
import com.facundo.mypokemonapp.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonUseCase: GetListPokemonUseCase,


    ) : ViewModel() {

    private val _pokemonValue = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())
    var pokemonValue: StateFlow<MutableList<Pokemon>> = _pokemonValue

    var state by mutableStateOf(UiState())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun onCreate() {
        viewModelScope.launch(Dispatchers.IO) {
            state = UiState(isLoading = true)
            state = UiState(isLoading = false, pokemon = pokemonUseCase())

            /*if (!result.isNullOrEmpty()) {
                _pokemonValue.value = result.toMutableList() ?: mutableListOf()
                Log.i("resultTest",result.toString())
                loading.value = false

            }*/
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: List<Pokemon> = emptyList()
    )
}