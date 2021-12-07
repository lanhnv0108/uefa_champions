package com.lanh.uefachampions.data.model

import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

data class Fixture(
    val id: Int,
    val date: String?,
    val home: Team?,
    val away: Team?,
    val goals: Goals?
) {
    fun mapItemSchedule(): ItemTeamMatchSchedule {
        return ItemTeamMatchSchedule(
            id = id,
            time = DateTime.parse(date).toDateTime(DateTimeZone.getDefault()).toString("HH:mm"),
            goalTeamOne = if (goals?.home == "null") null else goals?.home,
            goalTeamTwo = if (goals?.away == "null") null else goals?.away,
            teamOne = home,
            teamTwo = away
        )
    }
}

object FixtureEntry {
    const val FIXTURE = "fixture"
    const val DATE = "date"
    const val RESPONSE = "response"
    const val GOALS = "goals"
    const val HOME = "home"
    const val AWAY = "away"
    const val TEAMS = "teams"
}
