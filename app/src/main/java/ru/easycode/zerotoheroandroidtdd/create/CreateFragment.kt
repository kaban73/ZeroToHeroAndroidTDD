package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentPageBinding

class CreateFragment : AbstractFragment<FragmentPageBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentPageBinding =
        FragmentPageBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     }
}