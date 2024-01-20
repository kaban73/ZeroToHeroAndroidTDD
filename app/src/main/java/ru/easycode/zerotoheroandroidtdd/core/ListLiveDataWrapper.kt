package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.list.ItemUi

interface ListLiveDataWrapper {
    interface Read {
        fun liveData() : LiveData<List<ItemUi>>
    }
    interface Add {
        fun add(value: ItemUi)
    }
    interface Update {
        fun update(value: List<ItemUi>)
    }
    interface Delete {
        fun delete(item: ItemUi)
    }
    interface Mutable : Read, Update
    interface All : Mutable, Add, Delete
    class Base(
        private val liveData: MutableLiveData<List<ItemUi>> = MutableLiveData()
    ) : All {
        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun add(value: ItemUi) {
            val list= liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }
        override fun update(value: List<ItemUi>) {
            liveData.value = value
        }
        override fun delete(item: ItemUi) {
            val list= liveData.value?.toMutableList() ?: ArrayList()
            list.remove(item)
            update(list)
        }
    }
}