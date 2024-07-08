package com.facundo.mypokemonapp.domain

import com.facundo.mypokemonapp.data.PokeRepository
import com.facundo.mypokemonapp.domain.model.Pokemon
import javax.inject.Inject


class ToggleFavoriteUseCase @Inject constructor(private val repository: PokeRepository) {
    suspend operator fun invoke(pokemon: Pokemon) {
        repository.toggleFavorite(pokemon)
    }
}