package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var count : Count = Count.Base(2, 4)
    private lateinit var uiState: UiState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.incrementButton.setOnClickListener{
            uiState = count.increment(b.countTextView.text.toString())
            b.countTextView.text = uiState.getText()
            if (uiState.javaClass == UiState.Max("").javaClass)
                uiState.disable(b.incrementButton)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }
        if (uiState.javaClass == UiState.Max("").javaClass)
            uiState.disable(b.incrementButton)
    }

    companion object {
        private const val KEY = "key"
    }
}



