package ru.easycode.zerotoheroandroidtdd


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.actionButton.setOnClickListener{
            b.progressBar.visibility = View.VISIBLE
            b.actionButton.isEnabled = false

            b.actionButton.postDelayed({
                b.progressBar.visibility = View.GONE
                b.titleTextView.visibility = View.VISIBLE
                b.actionButton.isEnabled = true
            }, 3500)
        }
    }
}