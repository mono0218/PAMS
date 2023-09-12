package com.monodev.pams.API.DATA

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

class AppDao {
    @Entity
    data class History(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "content") val content: String?,
        @ColumnInfo(name = "time") val time: String?
    )

    @Dao
    interface UserDao {
        @Query("SELECT * FROM history")
        fun getAll(): List<History>

        @Insert
        fun insertAll(user: History)

        @Delete
        fun delete(user: History)
    }
}