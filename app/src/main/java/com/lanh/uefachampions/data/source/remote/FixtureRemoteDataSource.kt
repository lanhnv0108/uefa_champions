package com.lanh.uefachampions.data.source.remote

import android.util.Log
import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.source.FixtureDataSource
import com.lanh.uefachampions.data.source.remote.fetchjson.GetJsonFromUrl
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.TypeFootball
import com.lanh.uefachampions.utils.TypeModel

class FixtureRemoteDataSource private constructor() :
    FixtureDataSource.Remote {

    override fun getFixture(
        date: String,
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURE.path +
                Constant.BASE_DATE + date +
                Constant.BASE_LEAGUE +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrl(
            listener,
            TypeModel.FIXTURE
        ).execute(baseUrl)
        Log.e("xxx" , baseUrl.toString())
    }

    override fun getAllFixture(
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURE.path +
                Constant.BASE_LEAGUE_ALL +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrl(
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

    private object Holder {
        val INSTANCE = FixtureRemoteDataSource()
    }

    companion object {
        val instance: FixtureRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
