package com.lanh.uefachampions.screen.news

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.repository.NewsRepository

class NewsPresenter internal constructor(private val repository: NewsRepository?) :
    ContractNews.Presenter {

    private var view: ContractNews.View? = null

    override fun getNews() {
        view?.showLoading()
        repository?.getNews(object : OnFetchDataJsonListener<MutableList<News>>{
            override fun onSuccess(data: MutableList<News>) {
                view?.hiddenLoading()
                view?.onGetNewsSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.hiddenLoading()
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: ContractNews.View?) {
        this.view = view
    }
}
