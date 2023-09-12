package com.monodev.pams.api.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppDao.History::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): AppDao.UserDao
}

