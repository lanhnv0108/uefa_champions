package com.sun.uefascore.screen.favorite

import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.utils.BasePresenter

interface FavoriteContract {

    interface View {

        fun onDeleteTeamLocalSuccess()
        fun onDeletePlayersLocalSuccess()
        fun onGetFavoritesSuccess(teamDetails: MutableList<TeamDetail>)
        fun onFailed(idMessage: Int)
    }

    interface Presenter : BasePresenter<View> {

        fun onGetFavorites()
        fun onDeleteTeamLocal(idTeam: String)
        fun onDeletePlayersLocal(idTeam: String)
    }
}
