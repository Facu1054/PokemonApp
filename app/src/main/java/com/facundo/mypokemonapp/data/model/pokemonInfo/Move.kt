package com.facundo.mypokemonapp.data.model.pokemonInfo

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)