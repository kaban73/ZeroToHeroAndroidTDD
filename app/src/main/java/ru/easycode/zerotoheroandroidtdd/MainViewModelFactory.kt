package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class MainViewModelFactory(private val savedStateRegistry: SavedStateRegistryOwner) :
    AbstractSavedStateViewModelFactory(savedStateRegistry, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MainViewModel(liveDataWrapper = LiveDataWrapper.Base(), repository = Repository.Base()) as T
    }
}