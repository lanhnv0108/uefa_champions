package com.lanh.uefachampions.data.source.repository

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.NewsDataSource
import com.lanh.uefachampions.data.source.remote.NewsRemoteDataSource
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

class NewsRepository private constructor(
    private val remote: NewsDataSource.Remote
){

    fun getNews(
        listener: OnFetchDataJsonListener<MutableList<News>>
    ){
        remote.getNews(listener)
    }

    private object Holder {
        val INSTANCE = NewsRepository(
            remote = NewsRemoteDataSource.instance
        )
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
