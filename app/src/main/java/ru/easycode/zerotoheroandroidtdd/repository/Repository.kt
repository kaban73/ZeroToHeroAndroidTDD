package ru.easycode.zerotoheroandroidtdd.repository

interface Repository {
    interface Add {
        fun add(value : String)
    }
    interface Read {
        fun list(): List<String>
    }
    interface Mutable : Read, Add
    class Base(
        private val dataSource : ItemsDao,
        private val now : Now
    ) : Mutable {
        override fun list(): List<String> {
            val listDao = dataSource.list()
            val res : ArrayList<String> = ArrayList()
            listDao.forEach {
                res.add(it.text)
            }
            return res
        }

        override fun add(value: String) {
            dataSource.add(ItemCache(now.nowMillis(),value))
        }

    }
}