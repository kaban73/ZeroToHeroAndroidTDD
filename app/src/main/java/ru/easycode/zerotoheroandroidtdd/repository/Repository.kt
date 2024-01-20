package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.list.Item

interface Repository{
    interface Read {
        fun list() : List<Item>
    }
    interface Add {
        fun add(value : String) : Long
    }
    interface Delete {
        fun delete(id : Long)
        fun item(id : Long) : Item
    }
    interface Mutable : Read, Add
    interface All : Mutable,  Delete
    class Base(
        private val dataSource: ItemsDao,
        private val now : Now
    ) : All {
        override fun list(): List<Item> {
            val list = mutableListOf<Item>()
            dataSource.list().map {
                list.add(Item(it.id, it.text))
            }
            return list
        }
        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(itemCache.id, itemCache.text)
        }
        override fun add(value: String): Long {
            val id =now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }
        override fun delete(id: Long) {
            dataSource.delete(id)
        }

    }
}