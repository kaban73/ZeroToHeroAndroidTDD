package ru.easycode.zerotoheroandroidtdd.folder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.databinding.FolderListLayoutBinding
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.DetailsFolderUi

class FolderListFragment : AbstractFragment<FolderListLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderListLayoutBinding =
        FolderListLayoutBinding.inflate(inflater,container, false)

    private lateinit var viewModel: FolderListViewModel
    private val folderListAdapter = FolderListAdapter(detailsFolderUi = object : DetailsFolderUi {
        override fun detailsFolderUi(folderUi: FolderUi) {
            viewModel.folderDetails(folderUi)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(FolderListViewModel::class.java)
        binding.foldersRecyclerView.adapter = folderListAdapter
        viewModel.liveData().observe(viewLifecycleOwner) {
            folderListAdapter.update(it)
        }

        binding.addButton.setOnClickListener {
            viewModel.addFolder()
        }

        viewModel.init()
    }

}