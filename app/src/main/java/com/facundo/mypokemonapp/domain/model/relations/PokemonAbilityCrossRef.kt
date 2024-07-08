package com.facundo.mypokemonapp.domain.model.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
/*
@Entity(
    tableName = "pokemon_ability_cross_ref",
    primaryKeys = ["pokemonId", "abilityId"],
    foreignKeys = [
        ForeignKey(
            entity = Pokemon::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ability::class,
            parentColumns = ["id"],
            childColumns = ["abilityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PokemonAbilityCrossRef(
    val pokemonId: Int,
    val abilityId: Int
)

*/
