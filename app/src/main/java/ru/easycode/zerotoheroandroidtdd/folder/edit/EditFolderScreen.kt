package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.main.Screen

data class EditFolderScreen(
    private val folderId: Long,
    private val folderName : String) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, FolderEditFragment.newInstance(folderId,folderName))
            .commit()
    }
    }