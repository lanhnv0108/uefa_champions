package com.lanh.uefachampions.data.model

data class TopScorer(
    val index: String? = null,
    val player: Player?,
    val statistic: Statistic?
)

object TopScorerEntry {
    const val PLAYER = "player"
    const val STATISTIC = "statistics"
}
