package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface TopScorerDataSource {
    /**
     *  Local
     */
    interface Local

    /**
     *  Remote
     */
    interface Remote {
        fun <T> getDataTopScorer(
            season: String,
            listener: OnFetchDataJsonListener<T>
        )
    }
}
