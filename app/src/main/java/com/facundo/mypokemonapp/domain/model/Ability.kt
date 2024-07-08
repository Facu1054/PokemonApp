package com.facundo.mypokemonapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Ability(
    val nameAbility: String = "",
    val is_hidden: Boolean = false
)

