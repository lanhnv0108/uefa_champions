package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatisticData(
    @Expose
    @SerializedName("type")
    val type: String?,
    @Expose
    @SerializedName("value")
    val value: String?
)

const val SHOT_ON_GOAL = "Shots on Goal"
const val SHOT_OFF_GOAL = "Shots off Goal"
const val TOTAL_SHOTS = "Total Shots"
const val BLOCKED_SHOTS = "Blocked Shots"
const val SHOTS_INSIDE_BOX = "Shots insidebox"
const val SHOTS_OUTSIDE_BOX = "Shots outsidebox"
const val FOULS = "Fouls"
const val CORNER_KICKS = "Corner Kicks"
const val OFFSIDES = "Offsides"
const val BALL_POSSESSION = "Ball Possession"
const val YELLOW_CARDS = "Yellow Cards"
const val RED_CARDS = "Red Cards"
const val GOALKEEPER_SAVES = "Goalkeeper Saves"
const val TOTAL_PASSES = "Total passes"
const val PASSES_ACCURATE = "Passes accurate"
const val PASS_PERCENT = "Passes %"

val translateStaticsVN = mapOf(
    SHOT_ON_GOAL to "Sút vào khung thành",
    SHOT_OFF_GOAL to "Sút ra ngoài khung thành",
    TOTAL_SHOTS to "Tổng cú sút",
    BLOCKED_SHOTS to "Blocked Shots",
    SHOTS_INSIDE_BOX to "Shots insidebox",
    SHOTS_OUTSIDE_BOX to "Shots outsidebox",
    FOULS to "Fouls",
    CORNER_KICKS to "Corner Kicks",
    OFFSIDES to "Offsides",
    BALL_POSSESSION to "Ball Possession",
    YELLOW_CARDS to "Thẻ vàng",
    RED_CARDS to "Thẻ đỏ",
    GOALKEEPER_SAVES to "Goalkeeper Saves",
    TOTAL_PASSES to "Total passes",
    PASSES_ACCURATE to "Passes accurate",
    PASS_PERCENT to "Passes %"
)
