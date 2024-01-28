package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

class Core(
    private val context: Context
) {
    private val dataBase by lazy {
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }
    fun foldersDao() = dataBase.foldersDao()
    fun notesDao() = dataBase.notesDao()
}