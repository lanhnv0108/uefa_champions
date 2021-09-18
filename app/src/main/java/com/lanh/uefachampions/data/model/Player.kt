package com.lanh.uefachampions.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val id: Int?,
    val name: String?,
    val age: Int?,
    val nationality: String?,
    val height: String?,
    val weight: String?,
    val photo: String?
) : Parcelable

object PLayerEntry {
    const val ID = "id"
    const val NAME = "name"
    const val AGE = "age"
    const val NATIONALITY = "nationality"
    const val HEIGHT = "height"
    const val WEIGHT = "weight"
    const val PHOTO = "photo"
}
