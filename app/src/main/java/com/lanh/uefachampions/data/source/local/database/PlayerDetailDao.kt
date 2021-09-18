package com.lanh.uefachampions.data.source.local.database

import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.source.local.OnFetchDataLocalListener

interface PlayerDetailDao {

    fun save(
        entities: MutableList<PlayerDetail>,
        listener: OnFetchDataLocalListener<Long>
    )

    fun update(
        entity: PlayerDetail,
        listener: OnFetchDataLocalListener<Int>
    )

    fun deleteByIdTeam(
        idTeam: String,
        listener: OnFetchDataLocalListener<Int>
    )

    fun getEntity(
        idEntity: String,
        listener: OnFetchDataLocalListener<PlayerDetail>
    )

    fun getAllByIdTeam(
        idTeam: String,
        listener: OnFetchDataLocalListener<MutableList<PlayerDetail>>
    )
}
