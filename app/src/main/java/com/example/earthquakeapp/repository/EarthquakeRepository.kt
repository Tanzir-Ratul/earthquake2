package com.example.earthquakeapp.repository

import androidx.lifecycle.LiveData
import com.example.earthquakeapp.api.endpoint.network.ApiService
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.api.endpoint.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class EarthquakeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getEarthQuakeData(
        startTime: String,
        endTime: String,
        minmagnitude: Double
    ) = apiService.getEarthquakeData(startTime, endTime, minmagnitude)


//	Flow<Earthquake> {
//
//		//val endUrl:String = "query?format=geojson&starttime=2014-01-01&endtime=2014-01-02&minmagnitude=5"
//		return flow {
//			emit(
//				apiService.getEarthquakeData(startTime, endTime, minmagnitude)
//			)
//		}.flowOn(Dispatchers.IO)
//	}
}