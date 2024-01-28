package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.folder.list.FolderUi

interface FolderLiveDataWrapper{
    interface Increment {
        fun increment()
    }
    interface Decrement {
        fun decrement()
    }
    interface Update {
        fun update(folder: FolderUi)
    }
    interface Read {
        fun folderId(): Long
    }
    interface Rename {
        fun rename(newName: String)
    }
    interface Mutable : Update, Read
    class Base() : Mutable, Increment, Decrement, Rename {
        override fun increment() {
            TODO("Not yet implemented")
        }

        override fun decrement() {
            TODO("Not yet implemented")
        }

        override fun update(folder: FolderUi) {
            TODO("Not yet implemented")
        }

        override fun folderId(): Long {
            TODO("Not yet implemented")
        }

        override fun rename(newName: String) {
            TODO("Not yet implemented")
        }

    }
}