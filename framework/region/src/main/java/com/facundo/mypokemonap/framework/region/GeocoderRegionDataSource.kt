package com.facundo.mypokemonap.framework.region

import android.location.Geocoder
import android.util.Log
import com.facundo.mypokemonapp.domain.region.data.DEFAULT_REGION
import com.facundo.mypokemonapp.domain.region.data.LocationDataSource
import com.facundo.mypokemonapp.domain.region.data.RegionDataSource
import com.facundo.mypokemonapp.domain.region.entities.Location

import javax.inject.Inject

internal class GeocoderRegionDataSource @Inject constructor(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        Log.i("GeocoderRegionDataSource", "addresses: $addresses")
        val region = addresses.firstOrNull()?.countryCode
        return region!!
    }

}