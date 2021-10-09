package com.lanh.uefachampions.screen.news

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.screen.base.ContractLoading
import com.lanh.uefachampions.utils.BasePresenter

interface ContractNews {
    interface View : ContractLoading {
        fun onGetNewsSuccess(news: MutableList<News>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getNews()
    }
}