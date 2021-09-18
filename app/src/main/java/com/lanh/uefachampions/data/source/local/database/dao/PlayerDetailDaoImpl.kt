package com.lanh.uefachampions.data.source.local.database.dao

import android.content.ContentValues
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Player
import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.model.Statistic
import com.lanh.uefachampions.data.model.Team
import com.lanh.uefachampions.data.source.local.OnFetchDataLocalListener
import com.lanh.uefachampions.data.source.local.database.DatabaseHelper
import com.lanh.uefachampions.data.source.local.database.PlayerDetailDao
import com.lanh.uefachampions.data.source.local.database.table.PlayerDetailTable

class PlayerDetailDaoImpl private constructor(
    private val databaseHelper: DatabaseHelper
) : PlayerDetailDao {

    override fun save(
        entities: MutableList<PlayerDetail>,
        listener: OnFetchDataLocalListener<Long>
    ) {
        var isSuccess: Long? = null
        entities.map {
            val contentValue = ContentValues().apply {
                put(PlayerDetailTable.COLUMN_ID, it.player?.id)
                put(PlayerDetailTable.COLUMN_NAME, it.player?.name)
                put(PlayerDetailTable.COLUMN_AGE, it.player?.age)
                put(PlayerDetailTable.COLUMN_NATIONALITY, it.player?.nationality)
                put(PlayerDetailTable.COLUMN_HEIGHT, it.player?.height)
                put(PlayerDetailTable.COLUMN_WEIGHT, it.player?.weight)
                put(PlayerDetailTable.COLUMN_PHOTO, it.player?.photo)
                put(PlayerDetailTable.COLUMN_GOALS, it.statistic?.goals)
                put(PlayerDetailTable.COLUMN_ID_TEAM, it.statistic?.team?.id)
            }
            databaseHelper.writableDatabase.insert(
                PlayerDetailTable.TABLE_NAME,
                null,
                contentValue
            ).also {
                isSuccess = it
            }
        }
        isSuccess?.let {
            if (it > 0) {
                listener.onSuccess(it)
            } else {
                listener.onError(R.string.failed_to_save)
            }
        }
    }

    override fun update(
        entity: PlayerDetail,
        listener: OnFetchDataLocalListener<Int>
    ) = Unit

    override fun deleteByIdTeam(idTeam: String, listener: OnFetchDataLocalListener<Int>) {
        databaseHelper.writableDatabase.delete(
            PlayerDetailTable.TABLE_NAME,
            "${PlayerDetailTable.COLUMN_ID_TEAM}=?",
            arrayOf(idTeam)
        ).also {
            if (it > 0) {
                listener.onSuccess(it)
            } else {
                listener.onError(R.string.failed_to_delete)
            }
        }
    }

    override fun getEntity(
        idEntity: String,
        listener: OnFetchDataLocalListener<PlayerDetail>
    ) = Unit

    override fun getAllByIdTeam(
        idTeam: String,
        listener: OnFetchDataLocalListener<MutableList<PlayerDetail>>
    ) {
        val players = mutableListOf<PlayerDetail>()
        val cursor = databaseHelper.readableDatabase.query(
            PlayerDetailTable.TABLE_NAME,
            null,
            "${PlayerDetailTable.COLUMN_ID_TEAM}=?",
            arrayOf(idTeam),
            null,
            null,
            null,
            null,
        )
        if (cursor != null) {
            with(cursor) {
                while (moveToNext()) {
                    players.add(
                        PlayerDetail(
                            player = Player(
                                id = getInt(getColumnIndex(PlayerDetailTable.COLUMN_ID)),
                                name = getString(getColumnIndex(PlayerDetailTable.COLUMN_NAME)),
                                age = getInt(getColumnIndex(PlayerDetailTable.COLUMN_AGE)),
                                nationality = getString(getColumnIndex(PlayerDetailTable.COLUMN_NATIONALITY)),
                                height = getString(getColumnIndex(PlayerDetailTable.COLUMN_HEIGHT)),
                                weight = getString(getColumnIndex(PlayerDetailTable.COLUMN_WEIGHT)),
                                photo = getString(getColumnIndex(PlayerDetailTable.COLUMN_PHOTO))
                            ),
                            statistic = Statistic(
                                team = Team(
                                    id = getInt(getColumnIndex(PlayerDetailTable.COLUMN_ID_TEAM)),
                                    name = null,
                                    logo = null
                                ),
                                goals = getInt(getColumnIndex(PlayerDetailTable.COLUMN_GOALS))
                            )
                        )
                    )
                }
                listener.onSuccess(players)
            }
        } else {
            listener.onError(R.string.failed_to_fetch)
        }
    }

    companion object {

        @Volatile
        private var instance: PlayerDetailDaoImpl? = null

        fun getInstance(databaseHelper: DatabaseHelper): PlayerDetailDaoImpl =
            instance ?: synchronized(this) {
                instance ?: PlayerDetailDaoImpl(databaseHelper).also {
                    instance = it
                }
            }
    }
}
