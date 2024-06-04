package com.facundo.mypokemonapp.domain

import com.facundo.mypokemonapp.data.PokeRepository
import com.facundo.mypokemonapp.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    val repository: PokeRepository,

    ) {

    suspend operator fun invoke(id:Int): Pokemon {

        return if (
            repository.getPokemonDetail(id).pokemonName.isNotEmpty()
        ) {
            //Log.i("DesdeAPI", "Desde la base de datos con conexion a Internet")
            repository.getPokemonDetail(id)
        } else {
            //Log.i("DesdeDB", "Desde la base de datos")
            Pokemon()
        }

    }
}