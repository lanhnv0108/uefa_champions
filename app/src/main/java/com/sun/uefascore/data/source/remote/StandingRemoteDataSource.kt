package com.sun.uefascore.data.source.remote

import com.sun.uefascore.data.source.StandingDataSource
import com.sun.uefascore.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.TypeModel

class StandingRemoteDataSource private constructor() : StandingDataSource.Remote {

    private object Holder {
        val INSTANCE = StandingRemoteDataSource()
    }

    override fun <T> getDataStandingLeague(listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL + STANDINGS + Constant.BASE_SEASON + Constant.BASE_LEAGUE
        GetJsonFromUrl(listener, TypeModel.STANDING_LEAGUE).execute(stringUrl)
    }

    companion object {
        private const val STANDINGS = "standings?"
        val instance by lazy { Holder.INSTANCE }
    }
}
