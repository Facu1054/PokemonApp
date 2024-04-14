package com.facundo.mypokemonapp.domain


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.facundo.mypokemonapp.domain.model.Pokemon
import javax.inject.Inject

class GetListPokemonUseCase @Inject constructor(
    val repository: PokeRepository,

) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): List<Pokemon> {

        return if (
            repository.getPokemonList().isNotEmpty()
        ) {
            Log.i("DesdeAPI", "Desde la base de datos con conexion a Internet")
            repository.getPokemonList()
        } else {
            Log.i("DesdeDB", "Desde la base de datos")
            emptyList()
        }

    }
}

