package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var uiState: UiState
    private val count :Count = Count.Base(2, 4, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        uiState = count.initial(b.countTextView.text.toString())
        uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)

        b.incrementButton.setOnClickListener{
            uiState = count.increment(b.countTextView.text.toString())
            uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
        }

        b.decrementButton.setOnClickListener{
            uiState = count.decrement(b.countTextView.text.toString())
            uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }
        uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}