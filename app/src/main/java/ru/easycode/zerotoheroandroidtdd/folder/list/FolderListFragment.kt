package ru.easycode.zerotoheroandroidtdd.folder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.databinding.FolderListLayoutBinding
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment

class FolderListFragment : AbstractFragment<FolderListLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderListLayoutBinding =
        FolderListLayoutBinding.inflate(inflater,container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}