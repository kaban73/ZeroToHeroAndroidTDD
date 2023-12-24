package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val saveText = sharedPreferences.getString("SaveText",b.titleTextView.text.toString())
        b.titleTextView.text = saveText
        b.changeButton.setOnClickListener{
            b.titleTextView.text = getString(R.string.i_am_an_android_developer)

            val editor = sharedPreferences.edit()
            editor.putString("SaveText", b.titleTextView.text.toString())
            editor.apply()
        }
    }
}