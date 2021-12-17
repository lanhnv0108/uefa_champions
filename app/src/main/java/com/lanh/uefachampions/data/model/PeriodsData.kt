package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeriodsData(
    @Expose
    @SerializedName("first")
    val first: Int?,
    @Expose
    @SerializedName("second")
    val second: Int?
)