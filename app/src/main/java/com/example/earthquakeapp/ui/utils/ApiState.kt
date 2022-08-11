package com.example.earthquakeapp.ui.utils

import com.example.earthquakeapp.api.models.Earthquake

sealed class ApiState{
	object Loading: ApiState()
	data class ProgressState(val isShow: Boolean = false, val type: Int = 0) : ApiState()
	data class Failure(val message:String): ApiState()
	data class Success(val data: Earthquake): ApiState()
	data class Empty(val message: String): ApiState()
}
