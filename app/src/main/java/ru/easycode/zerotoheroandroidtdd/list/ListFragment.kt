package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentListBinding =
        FragmentListBinding.inflate(inflater,container, false)

    private val adapter = Adapter()
    private lateinit var viewModel: ListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(ListViewModel::class.java)

        binding.recyclerView.adapter = adapter
        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            viewModel.create()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState ?: Bundle()))
    }

}