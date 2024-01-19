package ru.easycode.zerotoheroandroidtdd.core

interface ListLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<List<String>>
    interface Update : LiveDataWrapper.Update<List<String>>
    interface Add {
        fun add(value: String)
    }
    interface Mutable : Read, Update
    interface All : Mutable, Add
    class Base :
        LiveDataWrapper.Abstract<List<String>>(), All {
        override fun add(value: String) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }

    }
}