package com.lanh.uefachampions.screen.detialnews

import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.NewsDetailRepository
import com.lanh.uefachampions.data.source.repository.NewsRepository
import com.lanh.uefachampions.screen.news.ContractNews

class NewsDetailPresenter internal constructor(private val repository: NewsDetailRepository?) :
    ContractNewsDetail.Presenter {

    private var view: ContractNewsDetail.View? = null

    override fun getDetailNews(slug: String) {
        repository?.getNewsDetail(slug,
            object : OnFetchDataJsonListener<NewsDetail> {
                override fun onSuccess(data: NewsDetail) {
                    view?.onGetNewsDetailSuccess(data)
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

    override fun setView(view: ContractNewsDetail.View?) {
        this.view = view
    }
}
