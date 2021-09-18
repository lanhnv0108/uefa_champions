package com.lanh.uefachampions.data.model

data class All(
    val played: Int?,
    val win: Int?,
    val draw: Int?,
    val lose: Int?
)

object AllEntry {
    const val PLAYED = "played"
    const val WIN = "win"
    const val DRAW = "draw"
    const val LOSE = "lose"
}
