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

    private lateinit var viewModel : CreateViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = viewModel.comeback()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(CreateViewModel::class.java)

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.createButton.setOnClickListener {
            hideKeyboard()
            val text = binding.inputEditText.text.toString()
            viewModel.add(text)
        }

        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled = it.toString().length > 2
        }
     }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}