package ru.easycode.zerotoheroandroidtdd.folder.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FolderCreateFragmentBinding

class FolderCreateFragment : AbstractFragment<FolderCreateFragmentBinding>() {
    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FolderCreateFragmentBinding =
        FolderCreateFragmentBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}