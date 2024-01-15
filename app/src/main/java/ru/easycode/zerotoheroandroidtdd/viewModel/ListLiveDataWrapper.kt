package ru.easycode.zerotoheroandroidtdd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent

interface ListLiveDataWrapper {
    fun liveData() : LiveData<List<CharSequence>>
    fun add(new : CharSequence)
    fun save(bundle : BundleWrapper.Save)
    fun update(list:List<CharSequence>)
    class Base(
        private val liveData: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent()
    ) : ListLiveDataWrapper {
        override fun liveData(): LiveData<List<CharSequence>> = liveData.map { it.toList() }
        override fun add(new: CharSequence) {
            val list = liveData.value ?: ArrayList()
            list.add(new)
            update(list)
        }
        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(liveData.value ?: ArrayList())
        }
        override fun update(list: List<CharSequence>) {
            liveData.value = ArrayList(list)
        }

    }
}