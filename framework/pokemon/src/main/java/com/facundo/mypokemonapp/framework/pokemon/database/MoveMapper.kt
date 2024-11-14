package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMove
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedMoveDetail
import com.facundo.mypokemonapp.domain.pokemon.model.SimplifiedVersionGroupDetail
import com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Move
import javax.inject.Inject

class MoveMapper @Inject constructor() {

    fun transformToSimplifiedMoves(originalMoves: List<Move>): List<SimplifiedMove> {
        return originalMoves.map { originalMove ->
            SimplifiedMove(
                move = SimplifiedMoveDetail(
                    name = originalMove.move.name
                ),
                versionGroupDetails = originalMove.version_group_details.map { detail ->
                    SimplifiedVersionGroupDetail(
                        levelLearnedAt = detail.level_learned_at,
                        moveLearnMethod = detail.move_learn_method.name,
                        versionGroup = detail.version_group.name
                    )
                }
            )
        }
    }
}