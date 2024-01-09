package ru.easycode.zerotoheroandroidtdd.Repository

import kotlinx.coroutines.delay
import ru.easycode.zerotoheroandroidtdd.Service.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.Service.SimpleService

interface Repository {
    suspend fun load() : SimpleResponse
    class Base(private val service: SimpleService,
               private val url:String) : Repository {
        override suspend fun load(): SimpleResponse {
            delay(3500)
            return service.fetch(url)
        }
    }

}
