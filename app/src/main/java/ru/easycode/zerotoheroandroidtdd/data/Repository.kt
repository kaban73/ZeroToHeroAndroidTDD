package ru.easycode.zerotoheroandroidtdd.data

import java.net.UnknownHostException

interface Repository {
    suspend fun load() : LoadResult
    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult =
            try {
                val response = service.fetch(url)
                LoadResult.Success(response)
            } catch (e : Exception) {
                LoadResult.Error(e is UnknownHostException)
            }
    }
}