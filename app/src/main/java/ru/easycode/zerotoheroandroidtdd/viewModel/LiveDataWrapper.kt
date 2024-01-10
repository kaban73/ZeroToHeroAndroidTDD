package ru.easycode.zerotoheroandroidtdd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.UiState

interface LiveDataWrapper {
    fun liveData() : LiveData<UiState>
    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)
    interface Update : LiveDataWrapper {

    }
    interface Mutable : Update, LiveDataWrapper {


    }
    class Base(private val liveData: MutableLiveData<UiState> = SingleLiveEvent())
        : Mutable, Update,  LiveDataWrapper {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData

    }
}