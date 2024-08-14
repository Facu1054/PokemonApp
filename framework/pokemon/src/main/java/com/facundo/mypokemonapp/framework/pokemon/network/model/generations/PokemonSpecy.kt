package com.facundo.mypokemonapp.framework.pokemon.network.model.generations

import android.util.Log
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.framework.pokemon.network.model.Result

data class PokemonSpecy(
    val name: String,
    val url: String,
    var region: String = ""
)

fun PokemonSpecy.toDomainModel(): Pokemon {
    val parseId = url.split("pokemon-species/").toTypedArray()
    val id = parseId[1].filter { it.isDigit() }

    return Pokemon(
        id = id.toInt(),
        pokemonName = name.replaceFirstChar { it.uppercase() },
        url = url,
        isFavorite = false,
        region = region

    )
}

