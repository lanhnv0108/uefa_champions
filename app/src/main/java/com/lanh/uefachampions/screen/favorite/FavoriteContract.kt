package com.lanh.uefachampions.screen.favorite

import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.utils.BasePresenter

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
