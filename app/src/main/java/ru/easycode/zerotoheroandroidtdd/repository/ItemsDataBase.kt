package ru.easycode.zerotoheroandroidtdd.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemCache::class], version = 1)
abstract class ItemsDataBase : RoomDatabase() {
    abstract fun itemsDao() : ItemsDao
}