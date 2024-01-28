package ru.easycode.zerotoheroandroidtdd.folder.list

interface FolderListLiveDataWrapper {
    interface UpdateListAndRead {
        fun update(list: List<FolderUi>)
    }
    interface Create {
        fun create(folderUi: FolderUi)
    }
    class Base : UpdateListAndRead, Create {
        override fun update(list: List<FolderUi>) {
            TODO("Not yet implemented")
        }

        override fun create(folderUi: FolderUi) {
            TODO("Not yet implemented")
        }

    }
}