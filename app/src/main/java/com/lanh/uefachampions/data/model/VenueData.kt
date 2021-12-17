package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VenueData(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("city")
    val city: String?,
    @Expose
    @SerializedName("address")
    val address: String?,
    @Expose
    @SerializedName("capacity")
    val capacity: Int?,
    @Expose
    @SerializedName("surface")
    val surface: String?,
    @Expose
    @SerializedName("image")
    val image: String?
)