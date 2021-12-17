package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lanh.uefachampions.screen.item.ItemTeamMatchSchedule
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


data class FixtureSeason(
    @Expose
    @SerializedName("fixture")
    val fixture: FixtureData?,
    @Expose
    @SerializedName("league")
    val leagueData: LeagueData,
    @Expose
    @SerializedName("teams")
    val teams: TeamsData?,
    @Expose
    @SerializedName("goals")
    val goals: GoalsData?,
    @Expose
    @SerializedName("score")
    val score: ScoreData?
) {
    fun mapItemSchedule(): ItemTeamMatchSchedule {
        return ItemTeamMatchSchedule(
            id = fixture?.id ?: -1,
            time = DateTime.parse(fixture?.date).toDateTime(DateTimeZone.getDefault())
                .toString("HH:mm"),
            home = teams?.home,
            away = teams?.away,
            goal = goals,
        )
    }
}

data class FixtureData(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("referee")
    val referee: String?,
    @Expose
    @SerializedName("timezone")
    val timezone: String?,
    @Expose
    @SerializedName("date")
    val date: String?,
    @Expose
    @SerializedName("timestamp")
    val timestamp: Int?,
    @Expose
    @SerializedName("periods")
    val periods: PeriodsData?,
    @Expose
    @SerializedName("venue")
    val venue: VenueData?,
    @Expose
    @SerializedName("status")
    val status: StatusData?
)
