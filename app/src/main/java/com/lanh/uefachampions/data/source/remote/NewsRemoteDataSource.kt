package com.lanh.uefachampions.data.source.remote

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.NewsDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrlWithGson
import com.lanh.uefachampions.utils.TypeModel

class NewsRemoteDataSource private constructor() :
    NewsDataSource.Remote {

    override fun getNews(listener: OnFetchDataJsonListener<MutableList<News>>) {
        val baseUrl = "https://livescore-football.p.rapidapi.com/soccer/news-list?page=1"
        GetJsonFromUrlWithGson(
            listener,
            TypeModel.NEWS
        ).execute(baseUrl)
    }

    private object Holder {
        val INSTANCE = NewsRemoteDataSource()
    }

    companion object {
        val instance: NewsRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
