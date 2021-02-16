package com.example.hotpeppergourmetsearch.gourmetShop

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofitによって検索する検索クエリの設定
interface HotPepperGourmetService {
    @GET("gourmet/v1/")
    suspend fun getGourmetShop(
        @Query("budget") feeCode1: String,  // 予算コード1（最大２個まで設定できる）
        @Query("budget") feeCode2: String,  // 予算コード2
        @Query("range") range: String,      // 検索範囲
        @Query("key")  key: String,         // APIキー
        @Query("lat") lat: String,          // 緯度
        @Query("lng") lng: String,          // 経度
        @Query("format") format: String,    // 形式（JSON)
    ): Response<GourmetShop>
}