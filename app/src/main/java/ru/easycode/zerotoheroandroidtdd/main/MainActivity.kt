package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.core.App
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)
        viewModel.init()

        val adapter = MyAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }
        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateBottomSheetDialog")
        }
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T =
        (application as App).viewModel(clasz)
}