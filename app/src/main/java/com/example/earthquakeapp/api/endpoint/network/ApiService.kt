package com.example.earthquakeapp.api.endpoint.network

import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.api.models.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("query?format=geojson")
    suspend fun getEarthquakeData(
        @Query("starttime") startTime: String? = "",
        @Query("endtime") endTime: String? = "",
        @Query("minmagnitude") minmagnitude: Double? = 0.0,
    ): NetworkResponse<Earthquake, ErrorResponse>

}

