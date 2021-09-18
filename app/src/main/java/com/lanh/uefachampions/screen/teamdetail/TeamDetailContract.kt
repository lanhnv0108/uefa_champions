package com.lanh.uefachampions.screen.teamdetail

import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.utils.BasePresenter
import java.lang.Exception

interface TeamDetailContract {

    interface View {

        fun onGetTeamByIdSuccess(teamDetail: TeamDetail)
        fun onGetPlayersByIdSuccess(playerDetails: MutableList<PlayerDetail>)
        fun onError(exception: Exception)
        fun onGetTeamLocalSuccess(teamDetail: TeamDetail)
        fun onGetPlayersLocalSuccess(playerDetails: MutableList<PlayerDetail>)
        fun onSaveTeamLocalSuccess()
        fun onSavePlayersLocalSuccess()
        fun onDeleteTeamLocalSuccess()
        fun onDeletePlayersLocalSuccess()
        fun onFailed(idMessage: Int)
    }

    interface Presenter : BasePresenter<View> {

        fun onGetTeamById(season: String, idTeam: String)
        fun onGetPlayersById(season: String, idTeam: String)
        fun onGetTeamLocal(idTeam: String)
        fun onGetPlayersLocal(idTeam: String)
        fun onSaveTeamLocal(teamDetail: TeamDetail)
        fun onSavePlayersLocal(playerDetails: MutableList<PlayerDetail>)
        fun onDeleteTeamLocal(idTeam: String)
        fun onDeletePlayersLocal(idTeam: String)
    }
}
