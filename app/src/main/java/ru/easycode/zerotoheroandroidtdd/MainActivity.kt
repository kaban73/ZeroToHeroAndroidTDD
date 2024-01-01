package ru.easycode.zerotoheroandroidtdd


import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var i : Int = 0
    private val mCountDownTimer = object : CountDownTimer(3500, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            i++
            b.progressBar.progress = i * 100 / (3500 / 1000)
        }

        override fun onFinish() {
            b.progressBar.visibility = View.GONE
            b.titleTextView.visibility = View.VISIBLE
            b.actionButton.isEnabled = true
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.actionButton.setOnClickListener{
            b.progressBar.visibility = View.VISIBLE
            b.actionButton.isEnabled = false
            mCountDownTimer.start()
        }
    }
}