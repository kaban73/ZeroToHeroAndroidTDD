package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {
    lateinit var factory: ProvideViewModel.Factory
    private val clearViewModels = object : ClearViewModels{
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach { factory.clear(it) }
        }
    }
    override fun onCreate() {
        super.onCreate()
        val provideViewModel = ProvideViewModel.Base(
            Core(this),
            clearViewModels
        )
        factory = ProvideViewModel.Factory(provideViewModel)
    }
    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)
}