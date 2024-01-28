package ru.easycode.zerotoheroandroidtdd.note.edit

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.main.Screen

data class EditNoteScreen(
    private val noteId : Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, NoteEditFragment(noteId))
            .commit()
    }
    }