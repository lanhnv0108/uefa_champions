package com.sun.uefascore.data.source.remote.fetchjson


import com.sun.uefascore.data.model.*
import org.json.JSONObject

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
}
