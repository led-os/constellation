package com.mooc.constellation.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mooc.constellation.bean.ConstellationBean
import com.mooc.constellation.dao.ConstellationDao

@Database(entities = [ConstellationBean::class], version = 1)
abstract class ConstellationDatabase : RoomDatabase() {
    abstract fun constellationDao(): ConstellationDao

    companion object {
        @Volatile
        private var instance: ConstellationDatabase? = null
        fun getInstance(context: Context): ConstellationDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ConstellationDatabase::class.java,
                    "constellation.db" //数据库名称
                ).createFromAsset("constellation.db")
//                    .allowMainThreadQueries()
                    .build()
            }
            return instance as ConstellationDatabase
        }
    }
}