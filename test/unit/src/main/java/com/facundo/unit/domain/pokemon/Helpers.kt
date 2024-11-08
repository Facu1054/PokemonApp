package com.facundo.unit.domain.pokemon

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon


fun samplePokemon(id:Int) = Pokemon(
    id = id,
    pokemonName = "Title $id",
    url = "",
    urlImage = "",
    type1 = "Fire",
    type2 = "",
    abilities = "Generic 1,Hidden Ability",
    statusAbility = "false,true",
    isFavorite = false
)



fun samplePokemons(vararg ids:Int) = ids.map { samplePokemon(it) }