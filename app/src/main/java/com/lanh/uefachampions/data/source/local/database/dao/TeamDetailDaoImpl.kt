package com.lanh.uefachampions.data.source.local.database.dao

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.data.source.local.OnFetchDataLocalListener
import com.lanh.uefachampions.data.source.local.database.TeamDetailDao
import com.lanh.uefachampions.data.source.local.database.DatabaseHelper
import com.lanh.uefachampions.data.source.local.database.table.TeamDetailTable

class TeamDetailDaoImpl private constructor(
    private val databaseHelper: DatabaseHelper
) : TeamDetailDao {

    override fun save(entity: TeamDetail, listener: OnFetchDataLocalListener<Long>) {
        val contentValue = ContentValues().apply {
            put(TeamDetailTable.COLUMN_ID, entity.id)
            put(TeamDetailTable.COLUMN_NAME, entity.name)
            put(TeamDetailTable.COLUMN_COUNTRY, entity.country)
            put(TeamDetailTable.COLUMN_FOUNDED, entity.founded)
            put(TeamDetailTable.COLUMN_LOGO, entity.logo)
        }
        databaseHelper.writableDatabase.insert(
            TeamDetailTable.TABLE_NAME,
            null,
            contentValue
        ).also {
            if (it > 0) {
                listener.onSuccess(it)
            } else {
                listener.onError(R.string.failed_to_save)
            }
        }
    }

    override fun update(entity: TeamDetail, listener: OnFetchDataLocalListener<Int>) {
        val contentValue = ContentValues().apply {
            put(TeamDetailTable.COLUMN_NAME, entity.name)
            put(TeamDetailTable.COLUMN_COUNTRY, entity.country)
            put(TeamDetailTable.COLUMN_FOUNDED, entity.founded)
            put(TeamDetailTable.COLUMN_LOGO, entity.logo)
        }
        databaseHelper.writableDatabase.update(
            TeamDetailTable.TABLE_NAME,
            contentValue,
            "${TeamDetailTable.COLUMN_ID}=?",
            arrayOf(entity.id.toString())
        ).also {
            if (it > 0) {
                listener.onSuccess(it)
            } else {
                listener.onError(R.string.failed_to_update)
            }
        }
    }

    override fun delete(idEntity: String, listener: OnFetchDataLocalListener<Int>) {
        databaseHelper.writableDatabase.delete(
            TeamDetailTable.TABLE_NAME,
            "${TeamDetailTable.COLUMN_ID}=?",
            arrayOf(idEntity)
        ).also {
            if (it > 0) {
                listener.onSuccess(it)
            } else {
                listener.onError(R.string.failed_to_delete)
            }
        }
    }

    override fun getEntity(idEntity: String, listener: OnFetchDataLocalListener<TeamDetail>) {
        val cursor = databaseHelper.readableDatabase.query(
            TeamDetailTable.TABLE_NAME,
            null,
            "${TeamDetailTable.COLUMN_ID}=?",
            arrayOf(idEntity.toString()),
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            with(cursor) {
                listener.onSuccess(
                    TeamDetail(
                        id = getInt(getColumnIndex(TeamDetailTable.COLUMN_ID)),
                        name = getString(getColumnIndex(TeamDetailTable.COLUMN_NAME)),
                        country = getString(getColumnIndex(TeamDetailTable.COLUMN_COUNTRY)),
                        founded = getInt(getColumnIndex(TeamDetailTable.COLUMN_FOUNDED)),
                        logo = getString(getColumnIndex(TeamDetailTable.COLUMN_LOGO))
                    )
                )
            }
        } else {
            listener.onError(R.string.failed_to_fetch)
        }
    }

    override fun getAll(listener: OnFetchDataLocalListener<MutableList<TeamDetail>>) {
        val teamDetails = mutableListOf<TeamDetail>()
        val cursor = databaseHelper.readableDatabase.query(
            TeamDetailTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            with(cursor) {
                while (moveToNext()) {
                    teamDetails.add(
                        TeamDetail(
                            id = getInt(getColumnIndex(TeamDetailTable.COLUMN_ID)),
                            name = getString(getColumnIndex(TeamDetailTable.COLUMN_NAME)),
                            country = getString(getColumnIndex(TeamDetailTable.COLUMN_COUNTRY)),
                            founded = getInt(getColumnIndex(TeamDetailTable.COLUMN_FOUNDED)),
                            logo = getString(getColumnIndex(TeamDetailTable.COLUMN_LOGO))
                        )
                    )
                }
            }
            listener.onSuccess(teamDetails)
        } else {
            listener.onError(R.string.failed_to_fetch)
        }
    }

    companion object {

        @Volatile
        private var instance: TeamDetailDaoImpl? = null

        fun getInstance(databaseHelper: DatabaseHelper): TeamDetailDaoImpl =
            instance ?: synchronized(this) {
                instance ?: TeamDetailDaoImpl(databaseHelper).also {
                    instance = it
                }
            }
    }
}
