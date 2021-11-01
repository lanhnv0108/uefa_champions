package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GoalsData(
    @Expose
    @SerializedName("home")
    val home: Int,
    @Expose
    @SerializedName("away")
    val away: Int
)