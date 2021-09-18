package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface StandingDataSource {
    /**
     *  Local
     */
    interface Local

    /**
     *  Remote
     */
    interface Remote {
        fun <T> getDataStandingLeague(season: String, listener: OnFetchDataJsonListener<T>)
    }
}
