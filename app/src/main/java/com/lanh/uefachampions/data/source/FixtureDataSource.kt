package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.model.FixtureDetailData
import com.lanh.uefachampions.data.model.FixtureSeason
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface FixtureDataSource {

    interface Remote {

        fun getFixture(
            date: String,
            season: String,
            listener: OnFetchDataJsonListener<MutableList<FixtureSeason>>
        )

        fun getAllFixture(
            season: String,
            listener: OnFetchDataJsonListener<MutableList<FixtureSeason>>
        )

        fun getSeason(listener: OnFetchDataJsonListener<MutableList<String>>)

        fun getFixtureDetail(
            id: String,
            listener: OnFetchDataJsonListener<List<FixtureDetailData>>
        )
    }
}
