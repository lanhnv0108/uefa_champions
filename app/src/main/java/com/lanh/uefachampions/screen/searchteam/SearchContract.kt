package com.lanh.uefachampions.screen.searchteam

import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.utils.BasePresenter

interface SearchContract {
    interface View {
        fun getTeamByNameSuccess(team: MutableList<TeamDetail>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTeamByName(name: String)
    }
}
