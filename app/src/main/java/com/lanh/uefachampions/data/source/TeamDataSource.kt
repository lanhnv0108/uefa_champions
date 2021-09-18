package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.data.source.local.OnFetchDataLocalListener
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface TeamDataSource {
    /**
     *  Local
     */
    interface Local {

        fun saveDataTeamDetail(
            teamDetail: TeamDetail,
            listener: OnFetchDataLocalListener<Long>
        )

        fun saveDataPlayerDetails(
            playerDetails: MutableList<PlayerDetail>,
            listener: OnFetchDataLocalListener<Long>
        )

        fun getDataTeamDetail(
            idTeam: String,
            listener: OnFetchDataLocalListener<TeamDetail>
        )

        fun getDataPlayerDetails(
            idTeam: String,
            listener: OnFetchDataLocalListener<MutableList<PlayerDetail>>
        )

        fun deleteDataTeamDetail(
            idTeam: String,
            listener: OnFetchDataLocalListener<Int>
        )

        fun deleteDataPlayerDetailsByIdTeam(
            idTeam: String,
            listener: OnFetchDataLocalListener<Int>
        )

        fun getAllDataTeamDetail(
            listener: OnFetchDataLocalListener<MutableList<TeamDetail>>
        )
    }

    /**
     *  Remote
     */
    interface Remote {

        fun <T> getDataTeamById(
            season: String,
            idTeam: String,
            listener: OnFetchDataJsonListener<T>
        )

        fun <T> getDataPlayersById(
            season: String,
            idTeam: String,
            listener: OnFetchDataJsonListener<MutableList<T>>
        )

        fun <T> getDataTeamByName(
            name: String,
            listener: OnFetchDataJsonListener<T>
        )
    }
}
