package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseDataApiFootBall<T>(
    @Expose
    @SerializedName("results")
    val result: Int,
    @Expose
    @SerializedName("paging")
    val paging: Paging,
    @Expose
    @SerializedName("response")
    val response: T
)

data class Paging(
    @Expose
    @SerializedName("current")
    val current: Int,
    @Expose
    @SerializedName("total")
    val total: Int
)
