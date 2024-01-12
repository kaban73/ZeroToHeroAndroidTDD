package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    interface Observe {
        fun liveData() : LiveData<String>
    }
    interface Save{
        fun save(bundleWrapper: BundleWrapper.Save)
    }
    interface Update{
        fun update(value : String)
    }
    interface Mutable : Observe, Save, Update
    class Base(
        private val liveData: MutableLiveData<String> = MutableLiveData()
    ): Mutable {
        override fun liveData(): LiveData<String> = liveData

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: String) {
            liveData.value = value
        }

    }
}