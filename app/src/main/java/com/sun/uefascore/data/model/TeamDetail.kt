package com.sun.uefascore.data.model

data class TeamDetail(
    val id: Int?,
    val name: String?,
    val country: String?,
    val founded: Int?,
    val logo: String?
)

object TeamDetailEntry {
    const val TEAM = "team"
    const val ID = "id"
    const val NAME = "name"
    const val COUNTRY = "country"
    const val FOUNDED = "founded"
    const val LOGO = "logo"
}
