package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val views : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.rootLayout)
        b.actionButton.setOnClickListener {
            val textView = TextView(this)
            textView.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            textView.text = b.inputEditText.text
            views.add(textView.text.toString())
            b.contentLayout.addView(textView)
            b.inputEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY, views)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listViews  = savedInstanceState.getStringArrayList(KEY)
        listViews?.forEach {
            val textView = TextView(this@MainActivity)
            textView.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            textView.text = it
            b.contentLayout.addView(textView)
        }
    }

    companion object {
        private const val KEY = "key"
    }
}