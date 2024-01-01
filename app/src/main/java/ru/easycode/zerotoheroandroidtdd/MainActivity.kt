package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.mvvm.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.mvvm.MainViewModel
import ru.easycode.zerotoheroandroidtdd.mvvm.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.actionButton.setOnClickListener{
            viewModel.load()
        }

        viewModel.liveData().observe(this) { uiState->
            uiState.apply(b.titleTextView, b.progressBar, b.actionButton)
        }

    }
}