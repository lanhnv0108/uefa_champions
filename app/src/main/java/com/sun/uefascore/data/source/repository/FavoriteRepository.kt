package com.sun.uefascore.data.source.repository

import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.TeamDataSource
import com.sun.uefascore.data.source.local.OnFetchDataLocalListener

class FavoriteRepository private constructor(
    private val local: TeamDataSource.Local
) {

    fun saveTeamDetail(
        teamDetail: TeamDetail,
        listener: OnFetchDataLocalListener<Long>
    ) {
        local.saveDataTeamDetail(teamDetail, listener)
    }

    fun savePlayerDetails(
        playerDetails: MutableList<PlayerDetail>,
        listener: OnFetchDataLocalListener<Long>
    ) {
        local.saveDataPlayerDetails(playerDetails, listener)
    }

    fun getTeamDetail(
        idTeam: String,
        listener: OnFetchDataLocalListener<TeamDetail>
    ) {
        local.getDataTeamDetail(idTeam, listener)
    }

    fun getPlayerDetails(
        idTeam: String,
        listener: OnFetchDataLocalListener<MutableList<PlayerDetail>>
    ) {
        local.getDataPlayerDetails(idTeam, listener)
    }

    fun deleteTeamDetail(
        idTeam: String,
        listener: OnFetchDataLocalListener<Int>
    ) {
        local.deleteDataTeamDetail(idTeam, listener)
    }

    fun deletePlayerDetailsByIdTeam(
        idTeam: String,
        listener: OnFetchDataLocalListener<Int>
    ) {
        local.deleteDataPlayerDetailsByIdTeam(idTeam, listener)
    }

    fun getAllTeamDetail(
        listener: OnFetchDataLocalListener<MutableList<TeamDetail>>
    ) {
        local.getAllDataTeamDetail(listener)
    }

    companion object {

        @Volatile
        private var instance: FavoriteRepository? = null

        fun getInstance(local: TeamDataSource.Local): FavoriteRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteRepository(local).also {
                    instance = it
                }
            }
    }
}
