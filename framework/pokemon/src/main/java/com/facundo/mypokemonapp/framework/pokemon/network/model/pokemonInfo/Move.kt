package com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)