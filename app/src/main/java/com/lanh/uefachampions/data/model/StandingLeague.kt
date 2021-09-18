package com.lanh.uefachampions.data.model

data class StandingLeague(
    val id: Int?,
    val name: String?,
    val standingGroups: MutableList<StandingGroup>?
)

object StandingLeagueEntry {
    const val ID = "id"
    const val NAME = "name"
    const val LEAGUE = "league"
}
