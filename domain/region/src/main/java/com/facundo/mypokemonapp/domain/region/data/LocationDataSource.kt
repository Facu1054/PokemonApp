package com.facundo.mypokemonapp.domain.region.data

import com.facundo.mypokemonapp.domain.region.entities.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}