package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface NoteListLiveDataWrapper {
    interface Create {
        fun create(noteUi: NoteUi)
    }
    interface Update {
        fun update(noteId: Long, newText: String)
    }
    interface UpdateList {
        fun updateList(notes: List<NoteUi>)
    }
    interface Read {
        fun liveData() : LiveData<List<NoteUi>>
    }
    interface UpdateListAndRead : UpdateList, Read
    class Base(
        private val liveData : MutableLiveData<List<NoteUi>> = MutableLiveData()
    ) : Create, Update, UpdateListAndRead {
        override fun create(noteUi: NoteUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(noteUi)
            updateList(list)
        }
        override fun update(noteId: Long, newText: String) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.find { noteId == it.id }?.let { list[list.indexOf(it)] = it.copy(title = newText) }
            updateList(list)
        }
        override fun updateList(notes: List<NoteUi>) {
            liveData.value = notes
        }

        override fun liveData(): LiveData<List<NoteUi>> =
            liveData
    }
}