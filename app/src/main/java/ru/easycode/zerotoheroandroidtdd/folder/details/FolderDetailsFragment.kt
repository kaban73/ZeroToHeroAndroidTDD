package ru.easycode.zerotoheroandroidtdd.folder.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FolderDetailsLayoutBinding

class FolderDetailsFragment : AbstractFragment<FolderDetailsLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FolderDetailsLayoutBinding =
        FolderDetailsLayoutBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}