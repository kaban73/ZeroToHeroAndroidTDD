package ru.easycode.zerotoheroandroidtdd

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

        if (savedInstanceState == null) {
            uiState = count.initial(b.countTextView.text.toString())
            uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
        }

        b.incrementButton.setOnClickListener{
            uiState = count.increment(b.countTextView.text.toString())
            uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
        }

        b.decrementButton.setOnClickListener{
            uiState = count.decrement(b.countTextView.text.toString())
            uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = count.initial(b.countTextView.text.toString())
        uiState.apply(b.countTextView, b.incrementButton, b.decrementButton)
    }
}