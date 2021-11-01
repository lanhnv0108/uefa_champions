package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamDetailsData(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("country")
    val country: String?,
    @Expose
    @SerializedName("founded")
    val founded: Int?,
    @Expose
    @SerializedName("national")
    val national: Boolean?,
    @Expose
    @SerializedName("logo")
    val logo: String?,
    @Expose
    @SerializedName("winner")
    val winner: Boolean?
)