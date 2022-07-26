package com.example.earthquakeapp.api.endpoint.network

import com.example.earthquakeapp.api.models.Earthquake
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
	):Earthquake
	//suspend fun getEarthquakeData(@Url url:String):Earthquake
}

//query?format=geojson&starttime=2014-01-01&endtime=2014-01-02&minmagnitude=5