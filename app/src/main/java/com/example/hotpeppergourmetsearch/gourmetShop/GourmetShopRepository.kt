package com.example.hotpeppergourmetsearch.gourmetShop

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Retrofit2の設定（ホットペッパーAPI）
    ホットペッパーAPIから店舗データを取得するために、Retrofit2の設定を行う
 */
class GourmetShopRepository {
    // 受け取ったJSON形式のデータをGsonにより、パースする
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://webservice.recruit.co.jp/hotpepper/")
        .build()
    private var hotPepperGourmetService: HotPepperGourmetService =
        retrofit.create(HotPepperGourmetService::class.java)

    // ホットペッパーAPIから周辺の店を検索し、レスポンスとして店舗データを受け取る
    suspend fun getGourmetShop(
        feeCode1: String,   // 予算コード1
        feeCode2: String,   // 予算コード2
        range: String,      // 検索範囲
        key: String,        // APIキー
        lat: String,        // 緯度
        lng: String,        // 経度
    ) =
        hotPepperGourmetService.getGourmetShop(feeCode1,
            feeCode2,
            range,
            key,
            lat,
            lng,
            format = "json")

    // GourmetShopRepositoryをシングルトンにする
    companion object Factory {
        val instance: GourmetShopRepository
            @Synchronized get() {
                return GourmetShopRepository()
            }
    }
}