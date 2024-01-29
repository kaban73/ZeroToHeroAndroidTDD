package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.NoteEditLayoutBinding

class NoteEditFragment : AbstractFragment<NoteEditLayoutBinding>() {
    companion object {
        fun newInstance(
            noteId: Long) : NoteEditFragment {
            val instance = NoteEditFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, noteId)
            }
            return instance
        }
        private const val KEY = "noteKey"
    }
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): NoteEditLayoutBinding =
        NoteEditLayoutBinding.inflate(inflater,container,false)
    private lateinit var viewModel: EditNoteViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(EditNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        viewModel.liveData().observe(viewLifecycleOwner) {
            binding.noteEditText.setText(it)
        }
        val noteId = requireArguments().getLong(KEY)
        binding.saveNoteButton.setOnClickListener {
            val text = binding.noteEditText.text.toString()
            viewModel.renameNote(noteId,text)
        }
        binding.deleteNoteButton.setOnClickListener {
            viewModel.deleteNote(noteId)
        }
        viewModel.init(noteId)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}