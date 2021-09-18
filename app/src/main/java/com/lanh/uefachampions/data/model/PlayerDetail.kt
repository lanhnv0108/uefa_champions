package com.lanh.uefachampions.data.model

data class PlayerDetail(
    val player: Player?,
    val statistic: Statistic?
)

object PlayerDetailEntry {
    const val PLAYER = "player"
    const val STATISTIC = "statistics"
}
