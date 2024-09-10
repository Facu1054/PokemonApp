package com.facundo.mypokemonapp.domain.region.data

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String
}