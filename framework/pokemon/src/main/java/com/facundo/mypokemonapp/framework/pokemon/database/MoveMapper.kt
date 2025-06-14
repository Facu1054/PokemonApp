package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMove
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMoveDetail
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedVersionGroupDetail
import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Move
import javax.inject.Inject

class MoveMapper @Inject constructor() {

    fun transformToSimplifiedMoves(originalMoves: List<Move>): List<SimplifiedMove> {
        return originalMoves.mapNotNull { originalMove ->
            // Filtramos los detalles del grupo de versiones que cumplen la condición
            val filteredVersionGroupDetails = originalMove.version_group_details.filter { detail ->
                detail.version_group.name == "scarlet-violet"
            }

            // Solo incluir si hay detalles que cumplen la condición
            if (filteredVersionGroupDetails.isNotEmpty()) {
                SimplifiedMove(
                    move = SimplifiedMoveDetail(
                        name = originalMove.move.name
                    ),
                    versionGroupDetails = filteredVersionGroupDetails.map { detail ->
                        SimplifiedVersionGroupDetail(
                            levelLearnedAt = detail.level_learned_at,
                            moveLearnMethod = detail.move_learn_method.name,
                            versionGroup = detail.version_group.name
                        )
                    }
                )
            } else {
                null // Excluir los movimientos que no cumplan la condición
            }
        }
    }
}