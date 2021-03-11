package com.sun.uefascore.data.source

import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener

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
