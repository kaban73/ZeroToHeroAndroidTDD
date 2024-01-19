package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.repository.Core

class App : Application(), ProvideViewModel {
    lateinit var factory: ViewModelFactory
    private val clear : ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) =
            factory.clearViewModel(clasz)

    }
    override fun onCreate() {
        super.onCreate()
        val provideViewModel = ProvideViewModel.Base(
            Core(this), clear
        )
        factory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T =
        factory.viewModel(clasz)
}