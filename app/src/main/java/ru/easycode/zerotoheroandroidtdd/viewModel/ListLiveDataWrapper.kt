package ru.easycode.zerotoheroandroidtdd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent

interface ListLiveDataWrapper {
    fun liveData() : LiveData<List<CharSequence>>
    fun add(new : CharSequence)
    fun save(bundle : BundleWrapper.Save)
    fun update(list:List<CharSequence>)
    class Base(
        private val liveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
    ) : ListLiveDataWrapper {
        private val list = ArrayList<CharSequence>()
        override fun liveData(): LiveData<List<CharSequence>> = liveData

        override fun add(new: CharSequence) {
            list.add(new)
            update(list)
        }

        override fun save(bundle: BundleWrapper.Save) {
            liveData.value?.let { ArrayList(it) }?.let { bundle.save(it) }
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }

    }
}