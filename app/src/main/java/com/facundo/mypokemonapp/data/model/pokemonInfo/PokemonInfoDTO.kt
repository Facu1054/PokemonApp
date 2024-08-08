package com.facundo.mypokemonapp.data.model.pokemonInfo

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon

data class PokemonInfoDTO(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

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