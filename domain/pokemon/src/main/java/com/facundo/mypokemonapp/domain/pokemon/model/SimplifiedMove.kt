package com.facundo.mypokemonapp.domain.pokemon.model

data class SimplifiedMove(
    val move: SimplifiedMoveDetail,
    val versionGroupDetails: List<SimplifiedVersionGroupDetail>
)

data class SimplifiedMoveDetail(
    val name: String
)

data class SimplifiedVersionGroupDetail(
    val levelLearnedAt: Int,
    val moveLearnMethod: String,
    val versionGroup: String
)