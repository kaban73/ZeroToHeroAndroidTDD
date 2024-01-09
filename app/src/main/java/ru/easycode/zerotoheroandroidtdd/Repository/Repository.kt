package ru.easycode.zerotoheroandroidtdd.Repository

import ru.easycode.zerotoheroandroidtdd.Service.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.Service.SimpleService

interface Repository {
    suspend fun load() : SimpleResponse
    class Base(private val service: SimpleService,
               private val url:String) : Repository {
        override suspend fun load(): SimpleResponse = service.fetch(url)
    }

}
