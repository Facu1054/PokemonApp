package com.facundo.mypokemonapp.domain.region.data

import com.facundo.mypokemonapp.domain.region.data.RegionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class RegionRepositoryTest{
    @Test
    fun `findLastRegion calls RegionsDataSource`() = runBlocking {
        val repository = RegionRepository(mock{
            onBlocking { findLastRegion() } doReturn "ES"
        })

        val region = repository.findLastRegion()

        assertEquals("ES", region)
    }
}