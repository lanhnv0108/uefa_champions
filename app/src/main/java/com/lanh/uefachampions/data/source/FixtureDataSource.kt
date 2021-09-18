package com.lanh.uefachampions.data.source

import com.lanh.uefachampions.data.model.Fixture
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener

interface FixtureDataSource {

    interface Remote {

        fun getFixture(
            date: String,
            season: String,
            listener: OnFetchDataJsonListener<MutableList<Fixture>>
        )

        fun getAllFixture(
            season: String,
            listener: OnFetchDataJsonListener<MutableList<Fixture>>
        )

        fun getSeason(listener: OnFetchDataJsonListener<MutableList<String>>)
    }
}
