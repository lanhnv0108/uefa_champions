package com.lanh.uefachampions.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FixtureSeason(
    @Expose
    @SerializedName("fixture")
    val fixtures: FixtureData?,
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
)

data class FixtureData(
    @Expose
    @SerializedName("id")
    val id: String,
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
