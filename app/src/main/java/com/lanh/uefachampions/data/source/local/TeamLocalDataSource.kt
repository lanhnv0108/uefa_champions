package com.lanh.uefachampions.data.source.local

import android.content.Context
import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.data.source.TeamDataSource
import com.lanh.uefachampions.data.source.local.database.DatabaseHelper
import com.lanh.uefachampions.data.source.local.database.PlayerDetailDao
import com.lanh.uefachampions.data.source.local.database.TeamDetailDao
import com.lanh.uefachampions.data.source.local.database.dao.PlayerDetailDaoImpl
import com.lanh.uefachampions.data.source.local.database.dao.TeamDetailDaoImpl

class TeamLocalDataSource private constructor(
    private val playerDetailDao: PlayerDetailDao,
    private val teamDetailDao: TeamDetailDao
) : TeamDataSource.Local {

    override fun saveDataTeamDetail(
        teamDetail: TeamDetail,
        listener: OnFetchDataLocalListener<Long>
    ) {
        teamDetailDao.save(teamDetail, listener)
    }

    override fun saveDataPlayerDetails(
        playerDetails: MutableList<PlayerDetail>,
        listener: OnFetchDataLocalListener<Long>
    ) {
        playerDetailDao.save(playerDetails, listener)
    }

    override fun getDataTeamDetail(
        idTeam: String,
        listener: OnFetchDataLocalListener<TeamDetail>
    ) {
        teamDetailDao.getEntity(idTeam, listener)
    }

    override fun getDataPlayerDetails(
        idTeam: String,
        listener: OnFetchDataLocalListener<MutableList<PlayerDetail>>
    ) {
        playerDetailDao.getAllByIdTeam(idTeam, listener)
    }

    override fun deleteDataTeamDetail(
        idTeam: String,
        listener: OnFetchDataLocalListener<Int>
    ) {
        teamDetailDao.delete(idTeam, listener)
    }

    override fun deleteDataPlayerDetailsByIdTeam(
        idTeam: String,
        listener: OnFetchDataLocalListener<Int>
    ) {
        playerDetailDao.deleteByIdTeam(idTeam, listener)
    }

    override fun getAllDataTeamDetail(
        listener: OnFetchDataLocalListener<MutableList<TeamDetail>>
    ) {
        teamDetailDao.getAll(listener)
    }

    companion object {

        @Volatile
        var instance: TeamLocalDataSource? = null

        fun getInstance(context: Context): TeamLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: TeamLocalDataSource(
                    PlayerDetailDaoImpl.getInstance(DatabaseHelper.getInstance(context)),
                    TeamDetailDaoImpl.getInstance(DatabaseHelper.getInstance(context))
                ).also {
                    instance = it
                }
            }
    }
}
