package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsDetail(
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("image")
    val image: ImageData?,
    @Expose
    @SerializedName("published")
    val published: String?,
    @Expose
    @SerializedName("article")
    val article: Article?,
    ) {
    fun convertArticleToText() =
        listOfNotNull(article).joinToString(separator = "\n\n") { it.p ?: "" + "\n" + it.h ?: "" }
}
data class ImageData(
    @Expose
    @SerializedName("url")
    val url: String?
)
