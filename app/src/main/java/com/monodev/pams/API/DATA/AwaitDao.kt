package com.monodev.pams.API.DATA

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AwaitDao {
    fun executeAwait(applicationContext: Context): List<Any> {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        val userDao = db.userDao()
        var users: List<Any> = listOf()

        runBlocking {
            withContext(Dispatchers.IO) {
                val DataWoker = Thread {
                    users = userDao.getAll();
                }
                DataWoker.start()
            }
        }
        return users
    }
}