package ru.easycode.zerotoheroandroidtdd.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.NoteCreateLayoutBinding

class NoteCreateFragment(
    private val folderId : Long) : AbstractFragment<NoteCreateLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): NoteCreateLayoutBinding =
        NoteCreateLayoutBinding.inflate(inflater, container, false)
    private lateinit var viewModel: CreateNoteViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(CreateNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.saveNoteButton.setOnClickListener {
            val text = binding.createNoteEditText.text.toString()
            viewModel.createNote(folderId,text)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}