package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScoreData(
    @Expose
    @SerializedName("halftime")
    val halftime: GoalsData?,
    @Expose
    @SerializedName("fulltime")
    val fullTime: GoalsData?,
    @Expose
    @SerializedName("extratime")
    val extraTime: GoalsData?,
    @Expose
    @SerializedName("penalty")
    val penalty: GoalsData?
)
