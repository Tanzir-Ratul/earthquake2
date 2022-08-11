package com.example.earthquakeapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.ui.repository.EarthquakeRepository
import com.example.earthquakeapp.ui.utils.ApiState
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel
@Inject constructor(private val repository: EarthquakeRepository) : ViewModel() {

    //private val earthquakeStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    //val _earthquakeStateFlow: StateFlow<ApiState> = earthquakeStateFlow
    val responseBody: MutableLiveData<Earthquake> = MutableLiveData()
    val startDateLV: MutableLiveData<String> = MutableLiveData()
    val endDateLV: MutableLiveData<String> = MutableLiveData()
    val magnitudeLV: MutableLiveData<Double> = MutableLiveData()
    var startDateE: String = ""
    var toDateE: String = ""
    var mag: Double = 5.0
    // val responseBody = MutableLiveData<Earthquake>()

    val apiState = MutableLiveData<ApiState>(ApiState.Loading)
    fun getData(): LiveData<Earthquake> {
        apiState.value = ApiState.ProgressState(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getEarthQuakeData(startDateE, toDateE, mag)
            withContext(Dispatchers.Main) {
                apiState.value = ApiState.ProgressState(false)
                when (response) {
                    is NetworkResponse.Success -> {
                        responseBody.value = response.body
                        apiState.value = ApiState.Success(response.body)
                    }
                    is NetworkResponse.ServerError -> {
                        val message =
                            "দুঃখিত, এই মুহূর্তে আমাদের সার্ভার কানেকশনে সমস্যা হচ্ছে, কিছুক্ষণ পর আবার চেষ্টা করুন"
                        apiState.value = ApiState.Failure(message)
                    }
                    is NetworkResponse.NetworkError -> {
                        val message = "দুঃখিত, এই মুহূর্তে আপনার ইন্টারনেট কানেকশনে সমস্যা হচ্ছে"
                        apiState.value = ApiState.Failure(message)
                    }
                    is NetworkResponse.UnknownError -> {
                        val message = "কোথাও কোনো সমস্যা হচ্ছে, আবার চেষ্টা করুন"
                        apiState.value = ApiState.Empty(message)
                    }
                }
            }

        }
        return responseBody
    }

    fun setSpinnerViewModel(mag: Double) {
        //magnitudeLV.value = mag
        this.mag = mag
    }

    fun setStartDate(startDt: String) {
        //startDateLV.value = startDt
        startDateE = startDt
    }

    fun setEndDate(endDt: String) {
        // endDateLV.value = endDt
        toDateE = endDt
    }

//val e = earthquakeLiveData.value


}


