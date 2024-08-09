package com.facundo.mypokemonapp.framework.pokemon.network.model

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon

data class Result(
    val name: String,
    val url: String
)

fun Result.toDomainModel(): Pokemon {
    val parseId = url.split("pokemon/").toTypedArray()
    val id = parseId[1].filter { it.isDigit() }



    return Pokemon(
        id = id.toInt(),
        pokemonName = name.replaceFirstChar { it.uppercase() },
        url = url,
        isFavorite = false

        //abilities = emptyList()
    )
}