package com.example.earthquakeapp.api.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("Message")
    var message: String? = ""
)

