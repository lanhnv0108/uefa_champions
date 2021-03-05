package com.sun.uefascore.data.model

data class Goals(
    val home: Int?,
    val away: Int?
)

object GoalsEntry {
    const val HOME = "home"
    const val AWAY = "away"
}
