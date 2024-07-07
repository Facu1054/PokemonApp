package com.facundo.mypokemonapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Ability(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nameAbility: String = "",
    val is_hidden: Boolean = false
)

