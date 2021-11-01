package com.lanh.uefachampions.screen.detialnews

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.screen.base.ContractLoading
import com.lanh.uefachampions.utils.BasePresenter

interface ContractNewsDetail {
    interface View {

        fun onGetNewsDetailSuccess(newsDetail: NewsDetail)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getDetailNews(slug: String)
    }
}
