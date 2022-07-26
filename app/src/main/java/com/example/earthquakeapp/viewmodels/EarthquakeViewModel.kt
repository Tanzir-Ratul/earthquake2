package com.example.earthquakeapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.repository.EarthquakeRepository
import com.example.earthquakeapp.utils.ApiState
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel
@Inject constructor(private val repository: EarthquakeRepository) : ViewModel() {

    //private val earthquakeStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    //val _earthquakeStateFlow: StateFlow<ApiState> = earthquakeStateFlow
    val earthquakeLiveData: MutableLiveData<Earthquake> = MutableLiveData()
    val startDate: MutableLiveData<String> = MutableLiveData()
    val endDate: MutableLiveData<String> = MutableLiveData()
    val magnitude: MutableLiveData<Double> = MutableLiveData()
    // val responseBody = MutableLiveData<Earthquake>()

    fun getData() {
        viewModelScope.launch {
            try {
                earthquakeLiveData.value = repository.getEarthQuakeData(
                    "2014-01-01",
                    "2014-01-02",
                    5.0
                )
            } catch (e: Exception) {

                Log.d("errorView", e.localizedMessage)
            }


        }
    }


    val e = earthquakeLiveData.value


}