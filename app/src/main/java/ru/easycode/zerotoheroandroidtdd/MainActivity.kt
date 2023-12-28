package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private var count : Count = Count.Base(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.incrementButton.setOnClickListener{
            b.countTextView.text = count.increment(count.getSteps())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, Count::class.java) as Count
        } else {
            savedInstanceState.getSerializable(KEY) as Count
        }
        b.countTextView.text = count.getSteps()
    }

    companion object {
        private const val KEY = "count"
    }
}

interface Count : Serializable {
    fun increment(number:String):String
    fun getSteps():String
    class Base(private var step: Int) : Count {
        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
        }
        override fun increment(number: String): String {
            step += number.toInt()
            return step.toString()
        }

        override fun getSteps(): String {
            return step.toString()
        }

    }
}