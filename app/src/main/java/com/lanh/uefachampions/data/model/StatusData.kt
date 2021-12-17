package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatusData(
    @Expose
    @SerializedName("long")
    val long: String,
    @Expose
    @SerializedName("short")
    val short: String,
    @Expose
    @SerializedName("elapsed")
    val elapsed: Int
)