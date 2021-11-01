package com.lanh.uefachampions.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class BaseDataApiNews<T>(
    @Expose
    @SerializedName("status")
    val status: Int,
    @Expose
    @SerializedName("data")
    val data: T?
)
