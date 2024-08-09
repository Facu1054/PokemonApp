package com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)