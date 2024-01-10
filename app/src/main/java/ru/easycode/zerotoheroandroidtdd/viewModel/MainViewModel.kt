package ru.easycode.zerotoheroandroidtdd.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.UiState
import ru.easycode.zerotoheroandroidtdd.data.Repository

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository) {
    private val viewModelScope = CoroutineScope ( SupervisorJob() + Dispatchers.Main.immediate )
    fun liveData() = liveDataWrapper.liveData()
    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            repository.load().show(liveDataWrapper as LiveDataWrapper.Update)
        }
    }
    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }
    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }
}