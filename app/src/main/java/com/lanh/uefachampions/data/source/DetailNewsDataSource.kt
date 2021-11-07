package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.model.NewsDetail
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface DetailNewsDataSource {

    interface Remote {

        fun getDetailNews(
            slug: String,
            listener: OnFetchDataJsonListener<NewsDetail>
        )

        fun getNews(
            listener: OnFetchDataJsonListener<MutableList<News>>
        )
    }
}
