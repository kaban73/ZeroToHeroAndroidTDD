package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentPageBinding

class CreateFragment : AbstractFragment<FragmentPageBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentPageBinding =
        FragmentPageBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (activity as ProvideViewModel).viewModel(CreateViewModel::class.java)

        val isCreateFragment = requireActivity().supportFragmentManager.fragments.last() == CreateFragment::class.java
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(
            isCreateFragment
        ) {
            override fun handleOnBackPressed() = viewModel.comeback()
        })

        binding.createButton.setOnClickListener {
            val text = binding.inputEditText.text.toString()
            viewModel.add(text)
        }

        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled = it.toString().length > 2
        }
     }
}