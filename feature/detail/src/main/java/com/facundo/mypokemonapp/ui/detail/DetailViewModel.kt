package com.facundo.mypokemonapp.ui.detail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundo.mypokemonapp.domain.pokemon.usecases.GetPokemonUseCase
import com.facundo.mypokemonapp.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.facundo.mypokemonapp.ui.detail.di.PokemonId
import com.facundo.mypokemonapp.domain.pokemon.model.Ability
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.ui.common.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.facundo.mypokemonapp.ui.common.Result
import com.facundo.mypokemonapp.ui.common.ifSuccess


sealed interface DetailAction {
    data object FavoriteClick : DetailAction
    data object MessageShown : DetailAction
}

//@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class DetailViewModel @Inject constructor(
    @PokemonId id: Int,
    pokemonUseCase: GetPokemonUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private var pokeId = MutableStateFlow(id)

    val state: StateFlow<Result<Pokemon>> = pokemonUseCase(id = id).stateAsResultIn(viewModelScope)


    var abilities = mutableStateOf<List<String>>(emptyList())
    var ability = mutableStateOf<List<Ability>>(emptyList())
    var isHidden = mutableStateOf<List<String>>(emptyList())



    init {
        viewModelScope.launch {
            state.collect { result ->
                when (result) {
                    is Result.Loading -> Log.d("DetailViewModel", "Loading data for ID $id...")
                    is Result.Success -> Log.d("DetailViewModel", "Data loaded successfully: ${result.data}")
                    is Result.Error -> Log.e("DetailViewModel", "Error loading data: ${result.exception}")
                }
            }
        }
    }

    fun onCreate(pokemon: Pokemon) {
        abilities.value = pokemon.abilities.split(",")
        isHidden.value = pokemon.statusAbility.split(",")

        ability.value = abilities.value.zip(isHidden.value) { ability, isHidden ->
            Ability(ability, isHidden.toBoolean())
        }

        Log.i("Pokemon", pokemon.toString())
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: Pokemon? = null,
        val message: String? = null
    )

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
                //Toast.makeText(null, "Pokemon Favorito $id", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

