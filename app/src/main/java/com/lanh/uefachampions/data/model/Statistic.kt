package com.lanh.uefachampions.data.model

data class Statistic(
    val team: Team?,
    val goals: Int?
)

object StatisticEntry {
    const val TEAM = "team"
    const val GOALS = "goals"
    const val TOTAL = "total"
}
