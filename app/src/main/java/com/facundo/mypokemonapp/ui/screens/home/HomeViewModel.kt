package com.facundo.mypokemonapp.ui.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
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
class HomeViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonUseCase,


) : ViewModel() {

    private val _pokemonValue = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())
    var pokemonValue: StateFlow<MutableList<Pokemon>> = _pokemonValue

    var loading = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    fun onCreate() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {

            val result = pokemonUseCase()

            if (!result.isNullOrEmpty()) {
                _pokemonValue.value = result.toMutableList() ?: mutableListOf()
                Log.i("resultTest",result.toString())
                loading.value = false

            }
        }
    }
}