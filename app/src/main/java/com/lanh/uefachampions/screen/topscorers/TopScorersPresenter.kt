package com.lanh.uefachampions.screen.topscorers

import com.lanh.uefachampions.data.model.TopScorer
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.TopScorerRepository

class TopScorersPresenter(
    private val topScorerRepository: TopScorerRepository?
) : TopScorersContract.Presenter {

    private var view: TopScorersContract.View? = null

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun setView(view: TopScorersContract.View?) {
        this.view = view
    }

    override fun getTopScorer(season: String) {
        topScorerRepository?.getTopScorer(
            season,
            object : OnFetchDataJsonListener<MutableList<TopScorer>> {

                override fun onSuccess(data: MutableList<TopScorer>) {
                    view?.onGetTopScorerSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    exception?.let { view?.onError(it) }
                }
            })
    }
}
