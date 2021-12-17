package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamsData(
    @Expose
    @SerializedName("home")
    val home: TeamDetailsData?,
    @Expose
    @SerializedName("away")
    val away: TeamDetailsData?
)