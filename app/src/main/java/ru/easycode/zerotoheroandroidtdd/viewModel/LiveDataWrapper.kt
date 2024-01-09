package ru.easycode.zerotoheroandroidtdd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.UiState
import ru.easycode.zerotoheroandroidtdd.bundle.BundleWrapper

interface LiveDataWrapper {
    fun liveData() : LiveData<UiState>
    fun update(value : UiState)
    fun save(bundleWrapper: BundleWrapper.Save)
    class Base
        (private val liveData: MutableLiveData<UiState> = SingleLiveEvent()) : LiveDataWrapper {
        override fun liveData(): LiveData<UiState> = liveData
        override fun update(value: UiState) {
            liveData.value = value
        }
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }
    }
}