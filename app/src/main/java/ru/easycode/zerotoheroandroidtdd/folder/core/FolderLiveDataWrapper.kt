package ru.easycode.zerotoheroandroidtdd.folder.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

interface FolderLiveDataWrapper{
    interface Read {
        fun liveData() : LiveData<FolderUi>
    }
    interface Increment {
        fun increment()
    }
    interface Decrement {
        fun decrement()
    }
    interface Update {
        fun update(folder: FolderUi)
    }
    interface FolderId {
        fun folderId(): Long
    }
    interface   FolderName {
        fun folderName() : String
    }
    interface Rename {
        fun rename(newName: String)
    }
    interface Mutable : Update, Read, FolderId, FolderName
    interface Edit : Increment, Decrement, Rename
    class Base(
        private val liveData : MutableLiveData<FolderUi> = MutableLiveData()
    ) : Mutable, Edit {
        override fun increment() {
            liveData.value?.let {
                var notesCount = it.notesCount
                val newFolder = it.copy(notesCount = ++notesCount)
                update(newFolder)
            }
        }

        override fun decrement() {
            liveData.value?.let {
                var notesCount = it.notesCount
                val newFolder = it.copy(notesCount = --notesCount)
                update(newFolder)
            }
        }

        override fun update(folder: FolderUi) {
            liveData.value = folder
        }

        override fun folderId(): Long =
            liveData.value?.id ?: -1

        override fun folderName(): String =
            liveData.value?.title ?: "This folder does not exists"

        override fun rename(newName: String) {
            liveData.value?.let {
                val newFolder = it.copy(title = newName)
                update(newFolder)
            }
        }
        override fun liveData(): LiveData<FolderUi> =
            liveData

    }
}