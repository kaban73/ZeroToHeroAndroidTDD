package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val viewModel = MainViewModel(KeepLiveData.liveData, Repository.Base())
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
}

object KeepLiveData {
    val liveData = LiveDataWrapper.Base()
}