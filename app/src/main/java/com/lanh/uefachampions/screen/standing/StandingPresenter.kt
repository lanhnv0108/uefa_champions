package com.lanh.uefachampions.screen.standing

import com.lanh.uefachampions.data.model.StandingLeague
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.StandingRepository

class StandingPresenter(private val standingRepository: StandingRepository?) :
    StandingContract.Presenter {

    private var view: StandingContract.View? = null

    override fun onStart() {
    }

    override fun onStop() = Unit

    override fun setView(view: StandingContract.View?) {
        this.view = view
    }

    override fun getStandingLeague(season: String) {
        standingRepository?.getStandingLeague(season,
            object : OnFetchDataJsonListener<StandingLeague> {

                override fun onSuccess(data: StandingLeague) {
                    view?.onGetStandingLeagueSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    exception?.let { view?.onError(it) }
                }
            })
    }
}
