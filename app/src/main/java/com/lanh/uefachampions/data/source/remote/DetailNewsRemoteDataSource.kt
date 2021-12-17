package com.lanh.uefachampions.data.source.remote

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.data.source.DetailNewsDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrlWithGson
import com.lanh.uefachampions.utils.TypeModel

class DetailNewsRemoteDataSource private constructor():
    DetailNewsDataSource.Remote{

    override fun getDetailNews(slug: String, listener: OnFetchDataJsonListener<NewsDetail>) {
        val baseUrl = "https://livescore-football.p.rapidapi.com/soccer/news-detail?slug=$slug"
        GetJsonFromUrlWithGson(
            listener,
            TypeModel.DETAIL_NEWS
        ).execute(baseUrl)
    }

    override fun getNews(listener: OnFetchDataJsonListener<MutableList<News>>) {

    }
    private object Holder {
        val INSTANCE = DetailNewsRemoteDataSource()
    }

    companion object {
        val instance: DetailNewsRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
