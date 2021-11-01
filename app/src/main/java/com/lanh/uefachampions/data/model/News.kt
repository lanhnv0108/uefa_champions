package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("title")
    val title: String?,

    @Expose
    @SerializedName("image")
    val image: String?,

    @Expose
    @SerializedName("published")
    val published: String?,

    @Expose
    @SerializedName("slug")
    val slug: String?
)

object NewsEntry {
    const val DATA = "data"
    const val TITLE = "title"
    const val IMAGE = "image"
    const val PUBLISHED = "published"
    const val SLUG = "slug"
}
