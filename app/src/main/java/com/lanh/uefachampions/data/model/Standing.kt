package com.lanh.uefachampions.data.model

data class Standing(
    val rank: Int?,
    val team: Team?,
    val points: Int?,
    val goalsDiff: Int?,
    val group: String?,
    val all: All?
)

object StandingEntry {
    const val RANK = "rank"
    const val TEAM = "team"
    const val POINTS = "points"
    const val GOALS_DIFF = "goalsDiff"
    const val GROUP = "group"
    const val ALL = "all"
}
