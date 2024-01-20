package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {
    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {
        private val map = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            val viewModel = map[clasz]
            return if (viewModel == null) {
                provideViewModel.viewModel(clasz).also {
                    map[clasz] = it
                }
            } else viewModel as T
        }

        override fun clearViewModel(clasz: Class<out ViewModel>) {
            map.remove(clasz)
        }

    }
}