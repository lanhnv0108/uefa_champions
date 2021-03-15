package com.sun.uefascore.data.source.local

import android.content.Context
import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.TeamDataSource
import com.sun.uefascore.data.source.local.database.DatabaseHelper
import com.sun.uefascore.data.source.local.database.PlayerDetailDao
import com.sun.uefascore.data.source.local.database.TeamDetailDao
import com.sun.uefascore.data.source.local.database.dao.PlayerDetailDaoImpl
import com.sun.uefascore.data.source.local.database.dao.TeamDetailDaoImpl

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
