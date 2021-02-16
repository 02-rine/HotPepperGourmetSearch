package com.example.hotpeppergourmetsearch.yahooTel

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelRepository {
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://map.yahooapis.jp/")
        .build()
    private var yahooService: YahooService =
        retrofit.create(YahooService::class.java)

    // APIから検索し,レスポンスを受け取る
    suspend fun getTel(appid: String, query: String) =
        yahooService.getTel(appid, query, "json", "mobile")

    // TelRepositoryをシングルトンにする
    companion object Factory {
        val instance: TelRepository
        @Synchronized get() {
            return TelRepository()
        }
    }
}