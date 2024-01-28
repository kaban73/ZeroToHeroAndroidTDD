package ru.easycode.zerotoheroandroidtdd.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.NoteCreateLayoutBinding

class NoteCreateFragment(
    private val folderId : Long) : AbstractFragment<NoteCreateLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): NoteCreateLayoutBinding =
        NoteCreateLayoutBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}