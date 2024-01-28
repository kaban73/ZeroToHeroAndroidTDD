package ru.easycode.zerotoheroandroidtdd.folder.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FolderEditLayoutBinding

class FolderEditFragment(
    private val folderId : Long
) : AbstractFragment<FolderEditLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderEditLayoutBinding =
        FolderEditLayoutBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}