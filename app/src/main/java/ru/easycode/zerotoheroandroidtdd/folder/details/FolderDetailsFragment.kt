package ru.easycode.zerotoheroandroidtdd.folder.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FolderDetailsLayoutBinding

class FolderDetailsFragment : AbstractFragment<FolderDetailsLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderDetailsLayoutBinding =
        FolderDetailsLayoutBinding.inflate(inflater,container,false)

    private lateinit var viewModel: FolderDetailsViewModel
    private val folderDetailsAdapter = FolderDetailsAdapter(object : DetailsNoteUi {
        override fun detailsNoteUi(noteUi: NoteUi) {
            viewModel.editNote(noteUi)
        }
    })
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(FolderDetailsViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        binding.notesRecyclerView.adapter = folderDetailsAdapter
        viewModel.liveData().observe(viewLifecycleOwner) {
            folderDetailsAdapter.update(it)
            binding.notesCountTextView.text = it.size.toString()
        }
        binding.editFolderButton.setOnClickListener {
            viewModel.editFolder()
        }
        binding.addNoteButton.setOnClickListener {
            viewModel.createNote()
        }
        viewModel.init()
        binding.folderNameTextView.text = viewModel.folderName()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}