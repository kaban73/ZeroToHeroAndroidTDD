package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.MutableLiveData

interface NoteListLiveDataWrapper {
    interface Create {
        fun create(noteUi: NoteUi)
    }
    interface Update {
        fun update(noteId: Long, newText: String)
    }
    interface UpdateListAndRead {
        fun update(notes: List<NoteUi>)
    }
    class Base(
        private val liveData : MutableLiveData<List<NoteUi>> = MutableLiveData()
    ) : Create, Update, UpdateListAndRead {
        override fun create(noteUi: NoteUi) {
        }
        override fun update(noteId: Long, newText: String) {
        }

        override fun update(notes: List<NoteUi>) {
            TODO("Not yet implemented")
        }

    }
}