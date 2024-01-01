package ru.easycode.zerotoheroandroidtdd.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.UiState

interface LiveDataWrapper {
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base (
        private val liveData : MutableLiveData<UiState> = MutableLiveData()
    ) : LiveDataWrapper {
        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData

    }
}