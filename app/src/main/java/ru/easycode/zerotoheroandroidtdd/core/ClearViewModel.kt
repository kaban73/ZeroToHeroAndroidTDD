package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun <T : ViewModel> clear(viewModelClass: Class<T>)
}