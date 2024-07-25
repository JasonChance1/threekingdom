package com.example.threekingdom.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.threekingdom.MainApplication
import com.example.threekingdom.database.dao.FuJiangDao
import com.example.threekingdom.database.entity.FuJiang

@Database(entities = [FuJiang::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun fuJiangDao(): FuJiangDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    MainApplication.instance(),
                    AppDatabase::class.java,
                    "ev"
                ).allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()  // 强制升级，清空数据库
                    .build()
            }
            return instance!!
        }
    }

}