package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import ru.easycode.zerotoheroandroidtdd.Repository.Repository
import ru.easycode.zerotoheroandroidtdd.Service.SimpleService
import ru.easycode.zerotoheroandroidtdd.viewModel.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.viewModel.MainViewModel

class App : Application() {
    lateinit var viewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(
            LiveDataWrapper.Base(),
            Repository.Base(SimpleService.Base(), URL))
    }
    companion object {
        private const val URL : String = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}