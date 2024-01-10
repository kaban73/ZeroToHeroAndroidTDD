package ru.easycode.zerotoheroandroidtdd.data

import java.net.UnknownHostException

interface Repository {
    suspend fun load() : LoadResult
    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                val response = service.fetch(url)
                LoadResult.Success(response)
            } catch (noConnectionException : UnknownHostException) {
                LoadResult.Error(true)
            } catch (otherException : Exception) {
                LoadResult.Error(false)
            }
        }

    }
}