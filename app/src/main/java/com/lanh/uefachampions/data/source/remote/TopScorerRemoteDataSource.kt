package com.lanh.uefachampions.data.source.remote

import com.lanh.uefachampions.data.source.TopScorerDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrl
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.TypeModel

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
