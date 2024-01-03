package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var viewModel : MainViewModel = MainViewModel(
        LiveDataWrapper.Base(),
        Repository.Base())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.actionButton.setOnClickListener{
            viewModel.load()
        }

        viewModel.liveData().observe(this) {uiState ->
            uiState.apply(b.titleTextView, b.actionButton, b.progressBar)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, viewModel)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, MainViewModel::class.java) as MainViewModel
        } else {
            savedInstanceState.getSerializable(KEY) as MainViewModel
        }
        viewModel.liveData().value?.apply(b.titleTextView, b.actionButton, b.progressBar)
    }

    companion object {
        private const val KEY = "viewModelKey"
    }
}