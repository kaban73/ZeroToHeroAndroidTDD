package ru.easycode.zerotoheroandroidtdd

import android.widget.EditText
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable
) : LiveDataWrapper.Observe {
    private val viewModelScope = CoroutineScope ( SupervisorJob() + Dispatchers.Main.immediate )
    override fun liveData(): LiveData<String> = liveDataWrapper.liveData()
    fun changeText(text :String, editText: EditText) {
        viewModelScope.launch {
            liveDataWrapper.update(text)
            editText.setText("")
        }
    }
    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }
    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }
}