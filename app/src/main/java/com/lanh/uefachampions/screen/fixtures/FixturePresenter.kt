package com.lanh.uefachampions.screen.fixtures

import com.lanh.uefachampions.data.model.FixtureSeason
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.FixtureRepository

class FixturePresenter internal constructor(private val repository: FixtureRepository?) :
    ContractFixture.Presenter {

    private var view: ContractFixture.View? = null

    override fun getFixture(date: String, season: String) {
        repository?.getFixture(
            date,
            season,
            object : OnFetchDataJsonListener<MutableList<FixtureSeason>> {
                override fun onSuccess(data: MutableList<FixtureSeason>) {
                    view?.onGetFixtureSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            })
    }

    override fun getAllFixture(season: String) {
        repository?.getAllFixture(
            season,
            object : OnFetchDataJsonListener<MutableList<FixtureSeason>> {
                override fun onSuccess(data: MutableList<FixtureSeason>) {
                    view?.onGetAllFixtureSuccess(data.apply { reverse() })
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }

    override fun getSeason() {
        repository?.getSeason(object : OnFetchDataJsonListener<MutableList<String>> {
            override fun onSuccess(data: MutableList<String>) {
                view?.getSeasonSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: ContractFixture.View?) {
        this.view = view
    }
}
