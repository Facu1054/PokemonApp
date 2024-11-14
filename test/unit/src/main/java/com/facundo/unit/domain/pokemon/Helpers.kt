package com.facundo.unit.domain.pokemon

import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMove
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMoveDetail


fun samplePokemon(id:Int) = Pokemon(
    id = id,
    pokemonName = "Title $id",
    url = "",
    urlImage = "",
    type1 = "Fire",
    type2 = "",
    abilities = "Generic 1,Hidden Ability",
    statusAbility = "false,true",
    isFavorite = false,

)
/*
fun sampleMoves(name: String)=SimplifiedMove(
    move = SimplifiedMoveDetail(name),
    versionGroupDetails = emptyList()
)
fun samplePokemons(vararg name:String) = name.map { sampleMoves(name) }*/


fun samplePokemons(vararg ids:Int) = ids.map { samplePokemon(it) }