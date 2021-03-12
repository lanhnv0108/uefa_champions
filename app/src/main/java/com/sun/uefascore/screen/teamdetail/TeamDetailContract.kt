package com.sun.uefascore.screen.teamdetail

import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.utils.BasePresenter
import java.lang.Exception

interface TeamDetailContract {

    interface View {

        fun onGetTeamByIdSuccess(teamDetail: TeamDetail)
        fun onGetPlayersByIdSuccess(playerDetails: MutableList<PlayerDetail>)
        fun onError(exception: Exception)
    }

    interface Presenter : BasePresenter<View> {

        fun onGetTeamById(season: String, idTeam: String)
        fun onGetPlayersById(season: String, idTeam: String)
    }
}
