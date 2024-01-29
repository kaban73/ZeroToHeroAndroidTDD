package ru.easycode.zerotoheroandroidtdd.note.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface NoteLiveDataWrapper {
    interface Read {
        fun liveData() : LiveData<String>
    }
    interface Update {
        fun update(noteText: String)
    }
    interface Mutable : Read, Update
    class Base(
        private val liveData : MutableLiveData<String> = MutableLiveData()
    ) : Mutable{
        override fun update(noteText: String) {
           liveData.value = noteText
        }
        override fun liveData(): LiveData<String> =
            liveData

    }
}