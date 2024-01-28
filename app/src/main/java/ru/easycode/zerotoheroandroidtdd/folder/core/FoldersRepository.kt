package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.core.FolderCache
import ru.easycode.zerotoheroandroidtdd.core.FoldersDao
import ru.easycode.zerotoheroandroidtdd.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.note.core.Now

interface FoldersRepository {
    interface Create {
        suspend fun createFolder(name: String): Long
    }
    interface ReadList {
        suspend fun folders(): List<Folder>
    }
    interface Edit {
        suspend fun delete(folderId: Long)
        suspend fun rename(folderId: Long, newName: String)
    }

    class Base(
        private val now : Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao
    ) : FoldersRepository, ReadList, Edit, Create {
        override suspend fun createFolder(name: String): Long {
            val id = now.timeInMillis()
            foldersDao.insert(FolderCache(id,name))
            return id
        }


        override suspend fun folders(): List<Folder> =
            foldersDao.folders().map {
                Folder(it.id, it.text, notesDao.notes(it.id).size)
            }

        override suspend fun delete(folderId: Long) =
             foldersDao.delete(folderId).also { notesDao.deleteByFolderId(folderId) }

        override suspend fun rename(folderId: Long, newName: String) {
            val folders = foldersDao.folders()
            folders.find { it.id == folderId }?.let {
                val newFolder = it.copy(text = newName)
                foldersDao.insert(newFolder)
            }
        }

    }
}