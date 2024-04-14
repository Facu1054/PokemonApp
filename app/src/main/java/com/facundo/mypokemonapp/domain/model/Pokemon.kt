package com.facundo.mypokemonapp.domain.model

import android.util.Log
import androidx.compose.ui.text.capitalize
import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO

data class Pokemon(
    val id: Int = 0,
    val pokemonName: String = "",
    val url: String = "",
    val urlImage: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    val type1: String = "",
    val type2: String = "",
    val description: String = ""
)


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

fun PokemonInfoDTO.toDomain(): Pokemon {

    return Pokemon (
        id = id,
        pokemonName = name.replaceFirstChar { it.uppercase() },
        type1 = types[0].type.name.replaceFirstChar { it.uppercase() },
        type2 = if (types.size > 1) types[1].type.name.replaceFirstChar { it.uppercase() } else "",
        description = "Height: $height\nWeight: $weight\nBase Experience: $base_experience"
    )
}