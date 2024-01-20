package ru.easycode.zerotoheroandroidtdd.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items_table")
    fun list() : List<ItemCache>
    @Query("SELECT * FROM items_table WHERE id=:id ")
    fun item(id : Long) : ItemCache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item : ItemCache)
    @Query("DELETE FROM items_table WHERE id=:id ")
    fun delete(id : Long)

}