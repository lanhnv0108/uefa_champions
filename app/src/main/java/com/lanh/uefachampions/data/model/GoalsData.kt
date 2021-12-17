package com.lanh.uefachampions.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoalsData(
    @Expose
    @SerializedName("home")
    val home: Int,
    @Expose
    @SerializedName("away")
    val away: Int
): Parcelable