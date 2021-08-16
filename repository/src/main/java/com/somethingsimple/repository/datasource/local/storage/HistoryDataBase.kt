package com.somethingsimple.repository.datasource.local.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.somethingsimple.repository.datasource.local.storage.dao.HistoryDao

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
