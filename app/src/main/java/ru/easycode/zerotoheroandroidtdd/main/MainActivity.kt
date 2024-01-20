package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.core.App
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.details.DetailsBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.details.DetailsItemUi
import ru.easycode.zerotoheroandroidtdd.list.Adapter

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = Adapter( detailsItemUi = object : DetailsItemUi {
        override fun details(itemId: Long) {
            DetailsBottomSheetFragment.newInstance(itemId).
                    show(supportFragmentManager, "CreateDetailsBottomSheetFragment")
        }
    }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)
        viewModel.init()
        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        binding.recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateAddBottomSheetFragment")
        }
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T =
        (application as App).viewModel(clasz)
}