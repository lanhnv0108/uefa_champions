package com.sun.uefascore.data.source.repository

import com.sun.uefascore.data.model.TopScorer
import com.sun.uefascore.data.source.TopScorerDataSource
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.remote.TopScorerRemoteDataSource

class TopScorerRepository private constructor(
    private val remote: TopScorerDataSource.Remote
) {

    private object Holder {
        val INSTANCE = TopScorerRepository(remote = TopScorerRemoteDataSource.instance)
    }

    fun getTopScorer(
        season: String,
        listener: OnFetchDataJsonListener<MutableList<TopScorer>>
    ) {
        remote.getDataTopScorer(season, listener)
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
