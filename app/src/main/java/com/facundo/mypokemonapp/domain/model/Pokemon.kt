package com.facundo.mypokemonapp.domain.model

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO

@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    var abilities: String = "",
    var statusAbility: String = "",
    //val moves: List<Move> = emptyList(),
    val isFavorite: Boolean 
)


/*


@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val pokemonName: String ,
    val url: String ,
    val urlImage: String ,
    val type1: String ,
    val type2: String ,
    val description: String ,
    val height: String ,
    val weight: String ,
    val base_experience: String ,
    val urlImageShiny: String ,
    val ability1: Ability ,
    val ability2: Ability ,
    val ability3: Ability ,
    val moves: List<Move>
)

fun Pokemon.createImageUrl(id: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

fun Pokemon.createShinyImageUrl(id: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/$id.png"
}
 */


fun Result.toDomainModel(): Pokemon {
    val parseId = url.split("pokemon/").toTypedArray()
    val id = parseId[1].filter { it.isDigit() }
    Log.i("Pokemon", "id: $id")



    return Pokemon(
        id = id.toInt(),
        pokemonName = name.replaceFirstChar { it.uppercase() },
        url = url,
        isFavorite = false

        //abilities = emptyList()
    )
}

fun PokemonInfoDTO.toDomainModel(): Pokemon {

    return Pokemon(
        id = id,
        pokemonName = name.replaceFirstChar { it.uppercase() },
        type1 = types[0].type.name.replaceFirstChar { it.uppercase() },
        type2 = if (types.size > 1) types[1].type.name.replaceFirstChar { it.uppercase() } else "",
        height = height.toString(),
        weight = weight.toString(),
        base_experience = base_experience.toString(),
        abilities = abilities[0].ability.name.replaceFirstChar { it.uppercase() } +
                if (abilities.size > 1) {
                    "," + abilities[1].ability.name.replaceFirstChar { it.uppercase() }
                } else ""
                        + if (abilities.size > 2) {
                            "," + abilities[2].ability.name.replaceFirstChar { it.uppercase() }
                        } else "",
        statusAbility = abilities[0].is_hidden.toString() + if (abilities.size > 1) {
            "," + abilities[1].is_hidden.toString()
        } else "" + if (abilities.size > 2) {
            "," + abilities[2].is_hidden.toString()
        } else "",
        isFavorite = false


        //abilities = abilities.map { Ability(nameAbility = it.ability.name.replaceFirstChar { it.uppercase() }, is_hidden = it.is_hidden)},
        //moves = moves
    )
}
/*
fun PokemonInfoDTO.AddAbility(): List<Ability>{
    return listOf(Ability(nameAbility = abilities[0].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[0].is_hidden),
        if (abilities.size > 1) Ability(nameAbility = abilities[1].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[1].is_hidden) else Ability(),
        if (abilities.size > 2) Ability(nameAbility = abilities[2].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[2].is_hidden) else Ability())
}*/


/*
fun PokemonInfoDTO.toDomainModel(): Pokemon {

    return Pokemon (
        id = id,
        pokemonName = name.replaceFirstChar { it.uppercase() },
        type1 = types[0].type.name.replaceFirstChar { it.uppercase() },
        type2 = if (types.size > 1) types[1].type.name.replaceFirstChar { it.uppercase() } else "",
        height = height.toString(),
        weight = weight.toString(),
        base_experience = base_experience.toString(),
        ability1 = Ability(nameAbility = abilities[0].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[0].is_hidden),
        ability2 = if (abilities.size > 1) Ability(nameAbility = abilities[1].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[1].is_hidden) else Ability(),
        ability3 = if (abilities.size > 2) Ability(nameAbility = abilities[2].ability.name.replaceFirstChar { it.uppercase() }, is_hidden = abilities[2].is_hidden) else Ability(),
        moves = moves
    )
}*/