package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.list.ItemUi

interface ListLiveDataWrapper {
    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }
    interface Add {
        fun add(value: ItemUi)
    }
    interface Delete {
        fun delete(item: ItemUi)
    }
    interface Update {
        fun update(list: List<ItemUi>)
        fun update(item: ItemUi)
    }
    interface Mutable : Read, Update
    interface All : Mutable, Add, Delete
    class Base(
        private val liveData : MutableLiveData<List<ItemUi>> = MutableLiveData()
    ) : All {
        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun update(list: List<ItemUi>) {
            liveData.value = list
        }

        override fun update(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.find { it.areItemsSame(item) }?.let{
                list[list.indexOf(it)] = item
            }
            update(list)
        }

        override fun add(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.remove(item)
            update(list)
        }

    }
}