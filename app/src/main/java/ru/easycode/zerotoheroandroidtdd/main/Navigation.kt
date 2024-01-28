package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface Navigation {
    interface Update {
        fun update(screen: Screen)
    }
    interface Read {
        fun liveData() : LiveData<Screen>
    }
    interface Mutable : Update, Read
    class Base(
        private val liveData: MutableLiveData<Screen> = MutableLiveData()
    ) : Mutable {
        override fun update(screen: Screen) {
            liveData.value = screen
        }
        override fun liveData(): LiveData<Screen> = liveData
    }
}