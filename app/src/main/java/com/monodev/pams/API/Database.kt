package com.monodev.pams.API

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class Database {
    companion object {
        lateinit var db : AppDatabase
        lateinit var dao : Sql.UserDao
    }

    @Database(entities = [Sql.History::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): Sql.UserDao

        companion object {
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase {
                return INSTANCE ?: synchronized(this) {

                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database")
                        .build()
                    INSTANCE = instance

                    instance
                }
            }
        }
    }
}