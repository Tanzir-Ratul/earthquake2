package com.example.earthquakeapp.utils

import com.example.earthquakeapp.api.models.Earthquake

sealed class ApiState{
	object Loading:ApiState()
	class Failure(val message:Throwable):ApiState()
	class Success(val data: Earthquake):ApiState()
	object Empty:ApiState()
}
