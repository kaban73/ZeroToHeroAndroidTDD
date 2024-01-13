package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.rootLayout)
        b.actionButton.setOnClickListener {
            addTextView(b.inputEditText.text.toString())
            b.inputEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val views = b.contentLayout.children.map { (it as TextView).text.toString() }.toList()
        outState.putStringArrayList(KEY, ArrayList(views))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listViews  = savedInstanceState.getStringArrayList(KEY)
        listViews?.forEach { addTextView(it) }
    }

    private fun addTextView(text : String) {
        val textView = TextView(this@MainActivity)
        textView.text = text
        b.contentLayout.addView(textView)
    }

    companion object {
        private const val KEY = "key"
    }
}