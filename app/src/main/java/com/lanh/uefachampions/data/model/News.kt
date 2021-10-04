package com.lanh.uefachampions.data.model

data class News (
    val title: String?,
    val image: String?,
    val published: String?,
    val slug: String?
)
object NewsEntry{
    const val DATA = "data"
    const val TITLE = "title"
    const val IMAGE = "image"
    const val PUBLISHED = "published"
    const val SLUG = "slug"
}
