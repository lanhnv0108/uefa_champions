package com.sun.uefascore.data.source.remote

import com.sun.uefascore.data.source.TopScorerDataSource
import com.sun.uefascore.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.TypeModel

class TopScorerRemoteDataSource private constructor() : TopScorerDataSource.Remote {

    private object Holder {
        val INSTANCE = TopScorerRemoteDataSource()
    }

    override fun <T> getDataTopScorer(
        season: String,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl = Constant.BASE_URL +
                TOP_SCORER +
                Constant.BASE_LEAGUE_ALL +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrl(listener, TypeModel.TOP_SCORER).execute(stringUrl)
    }

    companion object {

        private const val TOP_SCORER = "players/topscorers"
        val instance by lazy { Holder.INSTANCE }
    }
}
