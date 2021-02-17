package com.example.hotpeppergourmetsearch.yahooTel

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Retrofit2の設定（YahooAPI）
    YahooAPIから電話番号と店舗名を取得するために、Retrofit2の設定を行う
 */
class TelRepository {
    // 受け取ったJSON形式のデータをGsonにより、パースする
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://map.yahooapis.jp/")
        .build()
    private var yahooService: YahooService =
        retrofit.create(YahooService::class.java)

    // YahooAPIから単語（店舗名）を検索し、レスポンスとして電話番号と店舗名を受け取る
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