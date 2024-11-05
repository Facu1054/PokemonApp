package com.facundo.unit.domain.pokemon

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon


fun samplePokemon(id:Int) = Pokemon(
    id = id,
    pokemonName = "Title",
    url = "",
    urlImage = "",
    type1 = "Fire",
    type2 = "",
    abilities = "None,None2",
    statusAbility = "false,true",
    isFavorite = false
)



fun samplePokemons(vararg ids:Int) = ids.map { samplePokemon(it) }