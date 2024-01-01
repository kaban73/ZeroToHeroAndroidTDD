package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

interface UiState {
    fun apply(textView: TextView, progressBar: ProgressBar, button: Button)
    object ShowProgress : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
        }
    }
    object ShowData : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.visibility = View.VISIBLE
            button.isEnabled = true
            progressBar.visibility = View.GONE
        }


    }
}