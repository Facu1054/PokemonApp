package com.facundo.mypokemonapp.domain.pokemon.usecases

import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    val repository: PokeRepository,

    ) {

    operator fun invoke(id:Int): Flow<Pokemon> {
        //println("Entro al Use case $id")
        return repository.getPokemonDetail(id)
    }
}