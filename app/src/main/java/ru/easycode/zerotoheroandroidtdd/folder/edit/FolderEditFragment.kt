package ru.easycode.zerotoheroandroidtdd.folder.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FolderEditLayoutBinding

class FolderEditFragment : AbstractFragment<FolderEditLayoutBinding>() {
    companion object {
        fun newInstance(
            folderId: Long,
            folderName : String) : FolderEditFragment {
            val instance = FolderEditFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, folderId)
                putString(KEY,folderName)
            }
            return instance
        }
        private const val KEY = "folderKey"
    }
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderEditLayoutBinding =
        FolderEditLayoutBinding.inflate(inflater,container,false)
    private lateinit var viewModel: EditFolderViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(EditFolderViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        val folderId = requireArguments().getLong(KEY)
        val folderName = requireArguments().getString(KEY)

        binding.folderEditText.setText(folderName)
        binding.saveFolderButton.setOnClickListener {
            val text =  binding.folderEditText.text.toString()
            viewModel.renameFolder(folderId, text)
        }
        binding.deleteFolderButton.setOnClickListener {
            viewModel.deleteFolder(folderId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}