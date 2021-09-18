package com.lanh.uefachampions.data.source.remote

import com.lanh.uefachampions.data.source.TeamDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrl
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.TypeModel

class TeamRemoteDataSource private constructor() : TeamDataSource.Remote {

    private object Holder {
        val INSTANCE = TeamRemoteDataSource()
    }

    override fun <T> getDataTeamById(
        season: String,
        idTeam: String,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl =
            Constant.BASE_URL +
                    TEAM +
                    Constant.BASE_ID +
                    idTeam +
                    Constant.BASE_LEAGUE +
                    Constant.BASE_SEASON +
                    season
        GetJsonFromUrl(listener, TypeModel.TEAM_DETAIL).execute(stringUrl)
    }

    override fun <T> getDataPlayersById(
        season: String,
        idTeam: String,
        listener: OnFetchDataJsonListener<MutableList<T>>
    ) {
        val stringUrl =
            Constant.BASE_URL +
                    PLAYER +
                    Constant.BASE_TEAM +
                    idTeam +
                    Constant.BASE_LEAGUE +
                    Constant.BASE_SEASON +
                    season
        GetJsonFromUrl(listener, TypeModel.PLAYER_DETAIL).execute(stringUrl)
    }

    override fun <T> getDataTeamByName(
        name: String,
        listener: OnFetchDataJsonListener<T>
    ) {
        val baseUrl = Constant.BASE_URL +
                TEAM +
                Constant.SEARCH +
                name
        GetJsonFromUrl(listener, TypeModel.TEAM_SEARCH).execute(baseUrl)
    }

    companion object {

        private const val PLAYER = "players?"
        private const val TEAM = "teams?"
        val instance by lazy { Holder.INSTANCE }
    }
}
