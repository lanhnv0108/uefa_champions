package com.sun.uefascore.data.source.local.database

import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.local.OnFetchDataLocalListener

interface TeamDetailDao {

    fun save(entity: TeamDetail, listener: OnFetchDataLocalListener<Long>)

    fun update(entity: TeamDetail, listener: OnFetchDataLocalListener<Int>)

    fun delete(idEntity: String, listener: OnFetchDataLocalListener<Int>)

    fun getEntity(idEntity: String, listener: OnFetchDataLocalListener<TeamDetail>)

    fun getAll(listener: OnFetchDataLocalListener<MutableList<TeamDetail>>)
}
