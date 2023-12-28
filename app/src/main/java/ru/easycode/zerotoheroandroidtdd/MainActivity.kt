package ru.easycode.zerotoheroandroidtdd


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var count : Count = Count.Base(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.incrementButton.setOnClickListener{
            b.countTextView.text = count.increment(b.countTextView.text.toString())
        }
    }
}

interface Count : Serializable {
    fun increment(number:String):String
    class Base(private val step: Int) : Count {
        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
        }
        override fun increment(number: String): String {
            if (number.toIntOrNull() == null) throw IllegalStateException()
            return (step + number.toInt()).toString()
        }
    }
}