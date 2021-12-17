package com.lanh.uefachampions.screen.item

import com.lanh.uefachampions.data.model.TeamDetailsData

data class TopFixtureDetailItem(
    val teamHome: TeamDetailsData?,
    val teamAway: TeamDetailsData?,
    val goal: Pair<String, String>?
) {
    val goalString
        get() = "${goal?.first ?: "0"} - ${goal?.second ?: "0"}"
}
