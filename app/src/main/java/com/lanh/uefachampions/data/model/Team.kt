package com.lanh.uefachampions.data.model

data class Team(
    val id: Int?,
    val name: String?,
    val logo: String?
)

object TeamEntry {
    const val ID = "id"
    const val NAME = "name"
    const val LOGO = "logo"
}
