package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LeagueData(
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
    @SerializedName("logo")
    val logo: String?,
    @Expose
    @SerializedName("season")
    val season: Int?,
    @Expose
    @SerializedName("round")
    val round: String?,
)
