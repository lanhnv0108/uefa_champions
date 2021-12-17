package com.lanh.uefachampions.screen.fixturedetail

import com.lanh.uefachampions.data.model.FixtureDetailData
import com.lanh.uefachampions.utils.BasePresenter

interface FixtureDetailContact {
    interface View {
        fun onGetFixtureDetailSuccess(fixtureDetailData: List<FixtureDetailData>?)
        fun onLoading(isLoading: Boolean)
        fun onError(error: Throwable?)
    }

    interface Presenter : BasePresenter<View> {
        fun getFixtureDetail(id: Int, isShowLoading: Boolean = true)
    }
}
