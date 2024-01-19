package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.repository.Core
import ru.easycode.zerotoheroandroidtdd.repository.Now
import ru.easycode.zerotoheroandroidtdd.repository.Repository

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz : Class<T>) : T
    class Base(
        core : Core,
        private val clearViewModel: ClearViewModel
    ) : ProvideViewModel {
        private val repository = Repository.Base(core.dao(), Now.Base())
        private val listLiveDataWrapper = ListLiveDataWrapper.Base()
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
        override fun <T : ViewModel> viewModel(clasz: Class<T>) = when(clasz) {
            MainViewModel::class.java -> MainViewModel(repository, listLiveDataWrapper, dispatcher)
            AddViewModel::class.java -> AddViewModel(repository, listLiveDataWrapper, clearViewModel, dispatcher)
            else -> {throw IllegalStateException("unknown viewModelClass $clasz")}
        } as T
    }
}