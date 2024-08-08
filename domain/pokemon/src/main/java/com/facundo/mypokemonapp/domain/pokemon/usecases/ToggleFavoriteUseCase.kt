package com.facundo.mypokemonapp.domain.pokemon.usecases

import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import javax.inject.Inject


class ToggleFavoriteUseCase @Inject constructor(private val repository: PokeRepository) {
    suspend operator fun invoke(pokemon: Pokemon) {
        repository.toggleFavorite(pokemon)
    }
}