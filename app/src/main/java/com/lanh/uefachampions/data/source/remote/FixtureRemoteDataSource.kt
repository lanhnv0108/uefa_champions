package com.lanh.uefachampions.data.source.remote

import android.util.Log
import com.lanh.uefachampions.data.model.FixtureDetailData
import com.lanh.uefachampions.data.model.FixtureSeason
import com.lanh.uefachampions.data.source.FixtureDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrl
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrlWithGson
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.TypeFootball
import com.lanh.uefachampions.utils.TypeModel

class FixtureRemoteDataSource private constructor() :
    FixtureDataSource.Remote {

    override fun getFixture(
        date: String,
        season: String,
        listener: OnFetchDataJsonListener<MutableList<FixtureSeason>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURES.path +
                Constant.BASE_DATE + date +
                Constant.BASE_LEAGUE +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrlWithGson(
            listener,
            TypeModel.FIXTURE
        ).execute(baseUrl)
        Log.e("xxx", baseUrl.toString())
    }

    override fun getAllFixture(
        season: String,
        listener: OnFetchDataJsonListener<MutableList<FixtureSeason>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURES.path +
                Constant.BASE_LEAGUE_ALL +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrlWithGson(
            listener,
            TypeModel.FIXTURE
        ).execute(baseUrl)
    }

    override fun getSeason(listener: OnFetchDataJsonListener<MutableList<String>>) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.LEAGUE.path +
                TypeFootball.SEASON.path
        GetJsonFromUrl(
            listener,
            TypeModel.SEASON
        ).execute(baseUrl)
    }

    override fun getFixtureDetail(
        id: String,
        listener: OnFetchDataJsonListener<List<FixtureDetailData>>,
    ) {
        val baseUrl = Constant.BASE_URL + TypeFootball.FIXTURES.path +
                TypeFootball.STATISTICS.path + "?" + TypeFootball.FIXTURE.path + "=" + id
        GetJsonFromUrlWithGson(listener, typeMode = TypeModel.FIXTURE_DETAIL).execute(baseUrl)
    }

    private object Holder {
        val INSTANCE = FixtureRemoteDataSource()
    }

    companion object {
        val instance: FixtureRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
