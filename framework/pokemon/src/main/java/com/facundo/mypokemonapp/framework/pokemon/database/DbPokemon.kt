package com.facundo.mypokemonapp.framework.pokemon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbPokemon(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonName: String = "",
    val url: String = "",
    val urlImage: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    val type1: String = "",
    val type2: String = "",
    val description: String = "",
    val height: String = "",
    val weight: String = "",
    val base_experience: String = "",
    val urlImageShiny: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/$id.png",
    var abilities: String = "",
    var statusAbility: String = "",
    //val moves: List<Move> = emptyList(),
    val isFavorite: Boolean,
    var generacion: String = ""
)