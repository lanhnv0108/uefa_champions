package com.sun.uefascore.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sun.uefascore.data.source.local.database.table.PlayerDetailTable
import com.sun.uefascore.data.source.local.database.table.TeamDetailTable

class DatabaseHelper private constructor(context: Context) : SQLiteOpenHelper(
    context,
    NAME_DATABASE,
    null,
    VERSION_DATABASE
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreateTeamDetail = "CREATE TABLE " +
                "${TeamDetailTable.TABLE_NAME}(" +
                "${TeamDetailTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${TeamDetailTable.COLUMN_NAME} TEXT," +
                "${TeamDetailTable.COLUMN_COUNTRY} TEXT," +
                "${TeamDetailTable.COLUMN_FOUNDED} INTEGER," +
                "${TeamDetailTable.COLUMN_LOGO} TEXT)"
        val sqlCreatePlayerDetail = "CREATE TABLE " +
                "${PlayerDetailTable.TABLE_NAME}(" +
                "${PlayerDetailTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${PlayerDetailTable.COLUMN_NAME} TEXT," +
                "${PlayerDetailTable.COLUMN_AGE} INTEGER," +
                "${PlayerDetailTable.COLUMN_NATIONALITY} TEXT," +
                "${PlayerDetailTable.COLUMN_HEIGHT} TEXT," +
                "${PlayerDetailTable.COLUMN_WEIGHT} TEXT," +
                "${PlayerDetailTable.COLUMN_PHOTO} TEXT," +
                "${PlayerDetailTable.COLUMN_GOALS} INTEGER," +
                "${PlayerDetailTable.COLUMN_ID_TEAM} INTEGER)"
        db?.execSQL(sqlCreateTeamDetail)
        db?.execSQL(sqlCreatePlayerDetail)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) = Unit

    companion object {

        const val NAME_DATABASE = "Football.db"
        const val VERSION_DATABASE = 1

        @Volatile
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper =
            instance ?: synchronized(this) {
                instance ?: DatabaseHelper(context).also {
                    instance = it
                }
            }
    }
}
