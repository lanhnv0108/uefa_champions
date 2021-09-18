package com.lanh.uefachampions.screen.topscorers

import com.lanh.uefachampions.data.model.TopScorer
import com.lanh.uefachampions.utils.BasePresenter
import java.lang.Exception

interface TopScorersContract {

    interface View {

        fun onGetTopScorerSuccess(topScorers: MutableList<TopScorer>)
        fun onError(exception: Exception)
    }

    interface Presenter : BasePresenter<View> {

        fun getTopScorer(season: String)
    }
}
