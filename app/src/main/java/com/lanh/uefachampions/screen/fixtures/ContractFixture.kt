package com.lanh.uefachampions.screen.fixtures

import com.lanh.uefachampions.data.model.FixtureSeason
import com.lanh.uefachampions.utils.BasePresenter

interface ContractFixture {

    interface View {
        fun onGetFixtureSuccess(fixtures: MutableList<FixtureSeason>)
        fun onGetAllFixtureSuccess(fixtures: MutableList<FixtureSeason>)
        fun getSeasonSuccess(season: MutableList<String>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getFixture(date: String, season: String)
        fun getAllFixture(season: String)
        fun getSeason()
    }
}
