package com.sun.uefascore.data.model

data class PlayerDetail(
    val player: Player?,
    val statistic: Statistic?
)

object PlayerDetailEntry {
    const val PLAYER = "player"
    const val STATISTIC = "statistics"
}
