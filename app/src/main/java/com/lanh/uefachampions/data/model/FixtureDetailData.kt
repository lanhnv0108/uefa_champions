package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lanh.uefachampions.screen.item.InfoFixtureDetailItem
import com.lanh.uefachampions.screen.item.InfoMatchItem
import com.lanh.uefachampions.screen.item.TopFixtureDetailItem

data class FixtureDetailData(
    @Expose
    @SerializedName("team")
    val team: TeamDetailsData?,
    @Expose
    @SerializedName("statistics")
    val statistics: List<StatisticData>?
) {

    fun getInfo(type: String): String {
        return statistics?.firstOrNull { it.type.equals(type, true) }?.value ?: "0"
    }

    fun getInfoMatch(statisticsOther: List<StatisticData>): List<InfoMatchItem>? {
        return statistics?.zipWithNext { statisticHome, statisticAway ->
            InfoMatchItem(
                statisticHome.type,
                (statisticHome.value ?: "0") to (statisticAway.value ?: "0")
            )
        }
    }
}

fun List<FixtureDetailData>.mapToTopFixtureDetailItem(): TopFixtureDetailItem? {
    val teamHome = getOrNull(0)
    val teamAway = getOrNull(1)
    if (teamHome == null || teamAway == null) return null
    return TopFixtureDetailItem(
        teamHome = teamHome.team,
        teamAway = teamAway.team,
        goal = teamHome.getInfo(SHOT_ON_GOAL) to (teamAway.getInfo(SHOT_ON_GOAL))
    )
}

fun List<FixtureDetailData>.mapToInfoFixtureDetailItem(): InfoFixtureDetailItem? {
    val teamHome = getOrNull(0)
    val teamAway = getOrNull(1)
    if (teamHome == null || teamAway == null) return null
    val infoMatchItems = teamAway.statistics?.let { teamHome.getInfoMatch(it) } ?: return null
    fun getInfoItem(type: String) = infoMatchItems.firstOrNull { it.title.equals(type, true) }
    return InfoFixtureDetailItem(
        numberOfShots = getInfoItem(TOTAL_SHOTS),
        goal = getInfoItem(SHOT_ON_GOAL),
        totalShots = getInfoItem(TOTAL_SHOTS),
        yellowCard = getInfoItem(YELLOW_CARDS),
        redCard = getInfoItem(RED_CARDS)
    )
}
