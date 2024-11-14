package com.facundo.mypokemonapp.domain.pokemon.data

import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMove
import kotlinx.coroutines.flow.Flow

interface PokeMovesConverter {
    suspend fun getMoves(): Flow<List<SimplifiedMove>>
}
