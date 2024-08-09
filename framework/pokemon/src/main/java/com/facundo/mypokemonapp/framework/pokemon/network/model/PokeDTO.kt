package com.facundo.mypokemonapp.framework.pokemon.network.model

data class PokeDTO(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)