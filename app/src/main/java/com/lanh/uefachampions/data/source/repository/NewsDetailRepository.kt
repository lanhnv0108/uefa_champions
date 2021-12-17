package com.lanh.uefachampions.data.source.repository

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.data.source.DetailNewsDataSource
import com.lanh.uefachampions.data.source.NewsDataSource
import com.lanh.uefachampions.data.source.remote.DetailNewsRemoteDataSource
import com.lanh.uefachampions.data.source.remote.NewsRemoteDataSource
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

class NewsDetailRepository private constructor(
    private val remote: DetailNewsDataSource.Remote
) {

    fun getNewsDetail(
        slug: String,
        listener: OnFetchDataJsonListener<NewsDetail>
    ) {
        remote.getDetailNews(slug, listener)
    }

    fun getNews(
        listener: OnFetchDataJsonListener<MutableList<News>>
    ) {
        remote.getNews(listener)
    }

    private object Holder {
        val INSTANCE = NewsDetailRepository(
            remote = DetailNewsRemoteDataSource.instance
        )
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}