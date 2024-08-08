package com.facundo.mypokemonapp.domain.pokemon.usecases


import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListPokemonUseCase @Inject constructor(
    val repository: PokeRepository,

    ) {

     operator fun invoke(): Flow<List<Pokemon>> {

        /*return if (
            repository.getPokemonList().isNotEmpty()
        ) {
            Log.i("DesdeAPI", "Desde la base de datos con conexion a Internet")
            repository.getPokemonList()
        } else {
            Log.i("DesdeDB", "Desde la base de datos")
            emptyList()
        }*/

        return repository.pokemons

    }
}

