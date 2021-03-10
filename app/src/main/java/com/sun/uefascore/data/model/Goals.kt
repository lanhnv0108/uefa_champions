package com.sun.uefascore.data.model

data class Goals(
    val home: String?,
    val away: String?
)

object GoalsEntry {
    const val HOME = "home"
    const val AWAY = "away"
}
