package com.lanh.uefachampions.data.source.remote

import com.lanh.uefachampions.data.source.StandingDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrl
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.TypeModel

class StandingRemoteDataSource private constructor() : StandingDataSource.Remote {

    private object Holder {
        val INSTANCE = StandingRemoteDataSource()
    }

    override fun <T> getDataStandingLeague(season: String, listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL +
                STANDINGS +
                Constant.BASE_SEASON +
                season +
                Constant.BASE_LEAGUE
        GetJsonFromUrl(
            listener,
            TypeModel.STANDING_LEAGUE
        ).execute(stringUrl)
    }

    companion object {
        private const val STANDINGS = "standings?"
        val instance by lazy { Holder.INSTANCE }
    }
}
