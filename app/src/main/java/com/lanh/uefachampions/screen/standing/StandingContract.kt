package com.lanh.uefachampions.screen.standing

import com.lanh.uefachampions.data.model.StandingLeague
import com.lanh.uefachampions.utils.BasePresenter
import java.lang.Exception

interface StandingContract {

    interface View {

        fun onGetStandingLeagueSuccess(standingLeague: StandingLeague)
        fun onError(exception: Exception)
    }

    interface Presenter : BasePresenter<View> {

        fun getStandingLeague(season : String)
    }
}
