package com.example.earthquakeapp.ui.repository

import com.example.earthquakeapp.api.endpoint.network.ApiService
import javax.inject.Inject


class EarthquakeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getEarthQuakeData(
        startTime: String,
        endTime: String,
        minmagnitude: Double
    ) = apiService.getEarthquakeData(startTime, endTime, minmagnitude)

}