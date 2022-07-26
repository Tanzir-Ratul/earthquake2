package com.example.earthquakeapp.api.endpoint.network

import com.example.earthquakeapp.api.models.Earthquake
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
	suspend fun getEarthquakeData(startTime:String,endTime:String,minmagnitude:Double) = apiService.getEarthquakeData(startTime,endTime,minmagnitude)
}