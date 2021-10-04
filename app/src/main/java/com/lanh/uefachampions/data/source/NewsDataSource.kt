package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.model.News
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface NewsDataSource {

    interface Remote {

        fun getNews(
            listener: OnFetchDataJsonListener<MutableList<News>>
        )
    }
}
