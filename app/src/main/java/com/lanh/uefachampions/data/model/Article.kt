package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @Expose
    @SerializedName("p")
    val p: String?,
    @Expose
    @SerializedName("h")
    val h: String?
)