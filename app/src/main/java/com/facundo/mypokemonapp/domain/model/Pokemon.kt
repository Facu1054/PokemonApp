package com.facundo.mypokemonapp.domain.model

import android.util.Log
import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.Move
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO

data class Pokemon(
    val id: Int = 0,
    val pokemonName: String = "",
    val url: String = "",
    val urlImage: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    val type1: String = "",
    val type2: String = "",
    val description: String = "",
    val height: String = "",
    val weight: String = "",
    val base_experience: String = "",
    val urlImageShiny: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/$id.png",
    val ability1: Ability = Ability(),
    val ability2: Ability = Ability(),
    val ability3: Ability = Ability(),
    val moves: List<Move> = emptyList()
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
        height = height.toString(),
        weight = weight.toString(),
        base_experience = base_experience.toString(),
        ability1 = Ability(name = abilities[0].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[0].is_hidden),
        ability2 = if (abilities.size > 1) Ability(name = abilities[1].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[1].is_hidden) else Ability(),
        ability3 = if (abilities.size > 2) Ability(name = abilities[2].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[2].is_hidden) else Ability(),
        moves = moves
    )
}