package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.rootLayout)

        b.inputEditText.setOnClickListener {
            b.inputEditText.setSelection( b.inputEditText.length())
        }
        b.inputEditText.addTextChangedListener {
            b.actionButton.isEnabled = it.toString() == "min"
        }
        b.actionButton.setOnClickListener {
            b.titleTextView.text = b.inputEditText.text
            b.inputEditText.setText("")
        }
    }
}