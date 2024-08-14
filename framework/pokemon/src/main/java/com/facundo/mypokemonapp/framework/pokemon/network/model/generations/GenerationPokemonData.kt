package com.facundo.mypokemonapp.framework.pokemon.network.model.generations

data class GenerationPokemonData(
    val id: Int,
    val main_region: MainRegion,
    val name: String,
    val pokemon_species: List<PokemonSpecy>
)