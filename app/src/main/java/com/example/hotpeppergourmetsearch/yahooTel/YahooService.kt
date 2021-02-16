package com.example.hotpeppergourmetsearch.yahooTel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofitによって検索する検索クエリの設定
interface YahooService {
    @GET("search/local/V1/localSearch")
    suspend fun getTel(
        @Query("appid") appid: String,      // アプリケーションID
        @Query("query") query: String,      // 検索クエリ（店舗名）
        @Query("output") output: String,    // JSON形式
        @Query("device") device: String     // モバイル端末で表示
    ): Response<ShopTel>
}