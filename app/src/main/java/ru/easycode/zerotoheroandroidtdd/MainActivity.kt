package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b:ActivityMainBinding
    private var isHide : String = "NO"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.hideButton.setOnClickListener{
            b.titleTextView.visibility = View.GONE
            isHide = "YES"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("isHide", isHide)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.getString("isHide") == "YES")
            b.titleTextView.visibility = View.GONE
        else
            b.titleTextView.visibility = View.VISIBLE
        super.onRestoreInstanceState(savedInstanceState)
    }

}