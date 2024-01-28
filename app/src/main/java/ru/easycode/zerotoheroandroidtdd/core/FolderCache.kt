package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders_table")
data class FolderCache(
    @PrimaryKey @ColumnInfo(name = "id") val id : Long,
    @ColumnInfo(name = "text") val text : String
)