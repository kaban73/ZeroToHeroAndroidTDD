package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.removeButton.setOnClickListener{
            b.rootLayout.removeView(b.titleTextView)
            b.removeButton.isEnabled = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, b.rootLayout.findViewById<TextView>(R.id.titleTextView)?.toString())
        outState.putBoolean(KEY, b.removeButton.isEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getString(KEY) == null && !savedInstanceState.getBoolean(KEY)) {
            b.rootLayout.removeView(b.titleTextView)
            b.removeButton.isEnabled = false
        }
    }

    companion object {
        private const val KEY = "key"
    }
}
