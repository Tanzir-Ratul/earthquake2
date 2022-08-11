package com.example.earthquakeapp.api.models
import com.google.gson.annotations.SerializedName


data class Earthquake(
    @SerializedName("bbox")
    val bbox: List<Double>,
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("type")
    val type: String
) {
    data class Feature(
        @SerializedName("geometry")
        val geometry: Geometry,
        @SerializedName("id")
        val id: String,
        @SerializedName("properties")
        val properties: Properties,
        @SerializedName("type")
        val type: String
    ) {
        data class Geometry(
            @SerializedName("coordinates")
            val coordinates: List<Double>,
            @SerializedName("type")
            val type: String
        )

        data class Properties(
            @SerializedName("alert")
            val alert: String,
            @SerializedName("cdi")
            val cdi: Double,
            @SerializedName("code")
            val code: String,
            @SerializedName("detail")
            val detail: String,
            @SerializedName("dmin")
            val dmin: Double,
            @SerializedName("felt")
            val felt: Int,
            @SerializedName("gap")
            val gap: Double,
            @SerializedName("ids")
            val ids: String,
            @SerializedName("mag")
            val mag: Double,
            @SerializedName("magType")
            val magType: String,
            @SerializedName("mmi")
            val mmi: Double,
            @SerializedName("net")
            val net: String,
            @SerializedName("nst")
            val nst: Int,
            @SerializedName("place")
            val place: String,
            @SerializedName("rms")
            val rms: Double,
            @SerializedName("sig")
            val sig: Int,
            @SerializedName("sources")
            val sources: String,
            @SerializedName("status")
            val status: String,
            @SerializedName("time")
            val time: Long,
            @SerializedName("title")
            val title: String,
            @SerializedName("tsunami")
            val tsunami: Int,
            @SerializedName("type")
            val type: String,
            @SerializedName("types")
            val types: String,
            @SerializedName("tz")
            val tz: Any,
            @SerializedName("updated")
            val updated: Long,
            @SerializedName("url")
            val url: String
        )
    }

    data class Metadata(
        @SerializedName("api")
        val api: String,
        @SerializedName("count")
        val count: Int,
        @SerializedName("generated")
        val generated: Long,
        @SerializedName("status")
        val status: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )

}