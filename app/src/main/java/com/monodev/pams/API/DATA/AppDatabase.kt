package com.monodev.pams.API.DATA

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppDao.History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): AppDao.UserDao
}

