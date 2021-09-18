package com.lanh.uefachampions.data.source.repository

import com.lanh.uefachampions.data.model.StandingLeague
import com.lanh.uefachampions.data.source.StandingDataSource
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.data.source.remote.StandingRemoteDataSource

class StandingRepository private constructor(private val remote: StandingDataSource.Remote) {

    private object Holder {
        val INSTANCE = StandingRepository(remote = StandingRemoteDataSource.instance)
    }

    fun getStandingLeague(season: String, listener: OnFetchDataJsonListener<StandingLeague>) {
        remote.getDataStandingLeague(season, listener)
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
