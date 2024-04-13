package com.facundo.mypokemonapp.data.model

data class PokeDTO(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)