package com.sun.uefascore.screen.topscorers

import com.sun.uefascore.data.model.TopScorer
import com.sun.uefascore.utils.BasePresenter
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
