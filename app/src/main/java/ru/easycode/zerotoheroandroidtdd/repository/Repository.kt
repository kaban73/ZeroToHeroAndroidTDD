package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.list.Item

interface Repository {
    interface Read {
        fun list() : List<Item>
    }
    interface Add {
        fun add(value:String) : Long
    }
    interface Get {
        fun item(id: Long): Item
    }
    interface Delete {
        fun delete(id : Long)
    }
    interface Update {
        fun update(id: Long, newText : String)
    }
    interface Change : Get, Delete, Update
    interface Mutable : Read, Update, Add
    interface All : Mutable, Change
    class Base(
        private val dataSource : ItemsDao,
        private val now : Now
    ) : All {
        override fun list(): List<Item> =
            dataSource.list().map { Item(it.id,it.text) }

        override fun update(id: Long, newText: String) =
            dataSource.add(ItemCache(id, newText))
        override fun add(value: String) : Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }
        override fun item(id: Long) : Item =
            dataSource.item(id).let { Item(it.id,it.text) }

        override fun delete(id: Long) =
            dataSource.delete(id)

    }
}