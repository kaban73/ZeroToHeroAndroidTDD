package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var state : State = State.Initial
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.removeButton.setOnClickListener{
            state = State.Removed
            state.apply(b.rootLayout, b.titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(b.rootLayout, b.titleTextView)
    }

    companion object {
        private const val KEY: String = "isExists"
    }
}

interface State : Serializable {
    fun apply(linearLayout: LinearLayout, textView: TextView)

    object Initial : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}