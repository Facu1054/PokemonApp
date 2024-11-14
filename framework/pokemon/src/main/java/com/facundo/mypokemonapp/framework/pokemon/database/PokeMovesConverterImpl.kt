package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.data.PokeMovesConverter
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMove
import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Move
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokeMovesConverterImpl @Inject constructor(
    private val pokeDao: PokeDao,
    private val gson: Gson,
    private val moveMapper: MoveMapper
) : PokeMovesConverter {


        override suspend fun getMoves(): Flow<List<SimplifiedMove>> {
            return pokeDao.fetchAllPokemon().map { dbPokemonList ->
                dbPokemonList.flatMap { dbPokemon ->
                    // Delegar la deserialización a una función específica
                    val originalMoves = deserializeMoves(dbPokemon.moves)

                    // Utilizar el mapeador para transformar a modelos simplificados
                    moveMapper.transformToSimplifiedMoves(originalMoves)
                }
            }
        }

        // Función separada para deserializar los datos
        private fun deserializeMoves(movesJson: String): List<Move> {
            return try {
                gson.fromJson(movesJson, object : TypeToken<List<Move>>() {}.type) ?: emptyList()
            } catch (e: Exception) {
                // Manejar errores de deserialización si es necesario
                emptyList()
            }
        }
}

