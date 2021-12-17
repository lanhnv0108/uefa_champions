package com.lanh.uefachampions.screen.fixturedetail

import com.lanh.uefachampions.data.model.FixtureDetailData
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.FixtureRepository

class FixtureDetailPresenter(private val fixtureRepository: FixtureRepository) :
    FixtureDetailContact.Presenter {
    private var view: FixtureDetailContact.View? = null

    override fun getFixtureDetail(id: Int, isShowLoading: Boolean) {
        view?.onLoading(isShowLoading)
        fixtureRepository.getFixtureDetail(id.toString(),
            object : OnFetchDataJsonListener<List<FixtureDetailData>> {
                override fun onSuccess(data: List<FixtureDetailData>) {
                    view?.onLoading(false)
                    view?.onGetFixtureDetailSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onLoading(false)
                    view?.onError(exception)
                }

            })
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun setView(view: FixtureDetailContact.View?) {
        this.view = view
    }
}
