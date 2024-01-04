package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val viewModel = (application as App).viewModel

        b.actionButton.setOnClickListener{
            viewModel.load()
        }

        viewModel.liveData().observe(this) {uiState ->
            uiState.apply(b.titleTextView, b.actionButton, b.progressBar)
        }
    }
}
