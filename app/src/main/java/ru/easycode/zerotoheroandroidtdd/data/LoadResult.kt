package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.UiState
import ru.easycode.zerotoheroandroidtdd.viewModel.LiveDataWrapper

interface LoadResult {
    fun show(updateLiveData: LiveDataWrapper.Update)
    data class Success(val data : SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }
    }
    data class Error(val noConnection : Boolean): LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            if (noConnection)
                updateLiveData.update(UiState.ShowData("No internet connection"))
            else
                updateLiveData.update(UiState.ShowData("Something went wrong"))
        }
    }

}
