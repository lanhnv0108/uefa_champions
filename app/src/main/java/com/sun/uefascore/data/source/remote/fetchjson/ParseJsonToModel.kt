package com.sun.uefascore.data.source.remote.fetchjson


import com.sun.uefascore.data.model.*
import org.json.JSONObject
import com.sun.uefascore.utils.TypeModel
import org.json.JSONArray

@Suppress("UNCHECKED_CAST")
class ParseJsonToModel {

    fun parseJsonToFixture(jsonObject: JSONObject?): Fixture? =
        jsonObject?.run {
            Fixture(
                home = parseJsonToTeam(getJSONObject(FixtureEntry.TEAMS).getJSONObject(FixtureEntry.HOME)),
                away = parseJsonToTeam(getJSONObject(FixtureEntry.TEAMS).getJSONObject(FixtureEntry.AWAY)),
                goals = parseJsonToGoal(getJSONObject(FixtureEntry.GOALS))
            )
        }

    private fun parseJsonToTeam(jsonObject: JSONObject?): Team? =
        jsonObject?.run {
            Team(
                getInt(TeamEntry.ID),
                getString(TeamEntry.NAME),
                getString(TeamEntry.LOGO)
            )
        }

    private fun parseJsonToGoal(jsonObject: JSONObject?): Goals? =
        jsonObject?.run {
            Goals(
                getInt(GoalsEntry.HOME),
                getInt(GoalsEntry.AWAY)
            )
        }

    @Throws(Exception::class)
    fun parseJsonToStandingLeague(jsonObject: JSONObject?): StandingLeague? =
        jsonObject?.run {
            val parseDataWithJson = ParseDataWithJson()
            val standingGroup = parseDataWithJson.parseJson(
                jsonObject.toString(),
                TypeModel.STANDING_GROUP
            ) as MutableList<StandingGroup>
            StandingLeague(
                id = getInt(StandingLeagueEntry.ID),
                name = getString(StandingLeagueEntry.NAME),
                standingGroups = standingGroup
            )
        }

    @Throws(Exception::class)
    fun parseJsonToStandingGroup(jsonArray: JSONArray?): StandingGroup? =
        jsonArray?.run {
            val parseDataWithJson = ParseDataWithJson()
            val standings = parseDataWithJson.parseJson(
                jsonArray.toString(),
                TypeModel.STANDING
            ) as MutableList<Standing>?
            StandingGroup(standings = standings)
        }

    @Throws(Exception::class)
    fun parseJsonToStanding(jsonObject: JSONObject?): Standing? =
        jsonObject?.run {
            Standing(
                rank = getInt(StandingEntry.RANK),
                team = parseJsonToStandingTeam(getJSONObject(StandingEntry.TEAM)),
                points = getInt(StandingEntry.POINTS),
                goalsDiff = getInt(StandingEntry.GOALS_DIFF),
                group = getString(StandingEntry.GROUP),
                all = parseJsonToStandingAll(getJSONObject(StandingEntry.ALL))
            )
        }

    @Throws(Exception::class)
    private fun parseJsonToStandingTeam(jsonObject: JSONObject?): Team? =
        jsonObject?.run {
            Team(
                id = getInt(TeamEntry.ID),
                name = getString(TeamEntry.NAME),
                logo = getString(TeamEntry.LOGO)
            )
        }

    @Throws(Exception::class)
    private fun parseJsonToStandingAll(jsonObject: JSONObject?): All? =
        jsonObject?.run {
            All(
                played = getInt(AllEntry.PLAYED),
                win = getInt(AllEntry.WIN),
                draw = getInt(AllEntry.DRAW),
                lose = getInt(AllEntry.LOSE)
            )
        }
}
