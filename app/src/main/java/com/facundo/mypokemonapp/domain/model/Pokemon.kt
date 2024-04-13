package com.facundo.mypokemonapp.domain.model

import android.util.Log
import androidx.compose.ui.text.capitalize
import com.facundo.mypokemonapp.data.model.Result

data class Pokemon(val id: Int, val pokemonName: String, val url: String,val urlImage: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png")


fun Result.toDomain(): Pokemon {
    val parseId = url.split("pokemon/").toTypedArray()
    val id = parseId[1].filter { it.isDigit() }
    Log.i("Pokemon", "id: $id")



    return Pokemon(
        id = id.toInt(),
        pokemonName = name.replaceFirstChar { it.uppercase() },
        url = url
    )
}