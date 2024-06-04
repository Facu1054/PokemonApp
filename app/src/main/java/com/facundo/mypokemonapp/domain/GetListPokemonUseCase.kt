package com.facundo.mypokemonapp.domain


import com.facundo.mypokemonapp.data.PokeRepository
import com.facundo.mypokemonapp.domain.model.Pokemon
import javax.inject.Inject

class GetListPokemonUseCase @Inject constructor(
    val repository: PokeRepository,

    ) {

    suspend operator fun invoke(): List<Pokemon> {

        /*return if (
            repository.getPokemonList().isNotEmpty()
        ) {
            Log.i("DesdeAPI", "Desde la base de datos con conexion a Internet")
            repository.getPokemonList()
        } else {
            Log.i("DesdeDB", "Desde la base de datos")
            emptyList()
        }*/

        return if (
            repository.getPokemonList().isNotEmpty()
        ) {
            //Log.i("DesdeAPI", "Desde la base de datos con conexion a Internet")
            repository.getPokemonList()
        } else {
            //Log.i("DesdeDB", "Desde la base de datos")
            emptyList()
        }

    }
}

