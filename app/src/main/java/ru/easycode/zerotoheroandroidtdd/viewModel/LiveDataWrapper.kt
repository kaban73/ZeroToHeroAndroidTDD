package ru.easycode.zerotoheroandroidtdd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.UiState

interface LiveDataWrapper {
    interface Update {
        fun update(value: UiState)
    }
    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }
    interface Observe {
        fun liveData() : LiveData<UiState>
    }
    interface Mutable : Update,  Save, Observe
    class Base(private val liveData: MutableLiveData<UiState> = SingleLiveEvent())
        : Mutable {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData

    }
}