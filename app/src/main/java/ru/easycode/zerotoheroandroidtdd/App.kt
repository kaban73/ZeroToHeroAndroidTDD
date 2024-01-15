package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import ru.easycode.zerotoheroandroidtdd.viewModel.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.viewModel.MainViewModel

class App : Application() {
    lateinit var viewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(ListLiveDataWrapper.Base())
    }
}