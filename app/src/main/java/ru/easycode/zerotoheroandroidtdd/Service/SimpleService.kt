package ru.easycode.zerotoheroandroidtdd.Service

import retrofit2.http.GET
import retrofit2.http.Url

interface SimpleService {
    @GET
    suspend fun fetch(@Url url: String): SimpleResponse
    class Base : SimpleService {

        private val map = mutableMapOf<String, SimpleResponse>()

        init {
            map["https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"] = SimpleResponse(text = "Hello World From Web!")
        }

        override suspend fun fetch(url: String): SimpleResponse {
            return map[url]!!
        }
    }
}
