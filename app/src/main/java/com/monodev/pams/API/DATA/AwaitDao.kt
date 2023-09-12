package com.monodev.pams.API.DATA

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AwaitDao {
        fun execute(applicationContext: Context): List<AppDao.History> {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).fallbackToDestructiveMigration().build()
            val userDao = db.userDao()
            var users: List<AppDao.History> = listOf()
            runBlocking {
                withContext(Dispatchers.IO) {
                    users = userDao.getAll();
                    println(userDao.getAll())
                }
            }
            return users
        }
}