package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import java.io.Serializable

interface UiState : Serializable {
    fun getText():String
    fun disable(button: Button)
    data class Base(private val text : String):UiState {
        override fun getText():String {return text}
        override fun disable(button: Button) {
            button.isEnabled = false
        }
    }

    data class Max(private val text: String) : UiState {
        override fun getText():String {return text}
        override fun disable(button: Button) {
            button.isEnabled = false
        }
    }
}