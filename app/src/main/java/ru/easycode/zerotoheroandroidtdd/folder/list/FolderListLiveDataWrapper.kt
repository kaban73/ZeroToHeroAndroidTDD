package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface FolderListLiveDataWrapper {
    interface Create {
        fun create(folderUi: FolderUi)
    }
    interface Update {
        fun update(list: List<FolderUi>)
    }
    interface Read {
        fun liveData() : LiveData<List<FolderUi>>
    }
    interface UpdateListAndRead : Update, Read
    class Base(
        private val liveData: MutableLiveData<List<FolderUi>> = MutableLiveData()
    ) : UpdateListAndRead, Create, Read {
        override fun update(list: List<FolderUi>) {
            liveData.value = list
        }

        override fun liveData(): LiveData<List<FolderUi>> =
            liveData

        override fun create(folderUi: FolderUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(folderUi)
            update(list)
        }

    }
}