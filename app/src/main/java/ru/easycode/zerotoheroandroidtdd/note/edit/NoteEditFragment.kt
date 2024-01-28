package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.NoteEditLayoutBinding

class NoteEditFragment(
    private val noteId : Long
) : AbstractFragment<NoteEditLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): NoteEditLayoutBinding =
        NoteEditLayoutBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}