package com.example.hotpeppergourmetsearch.gourmetShop

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GourmetShopRepository {
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://webservice.recruit.co.jp/hotpepper/")
        .build()
    private var hotPepperGourmetService: HotPepperGourmetService =
        retrofit.create(HotPepperGourmetService::class.java)

    // APIから検索し,レスポンスを受け取る
    suspend fun getGourmetShop(feeCode1: String, feeCode2:String, range: String, key: String, lat: String, lng: String) =
        hotPepperGourmetService.getGourmetShop(feeCode1, feeCode2, range, key, lat, lng, format = "json")

    // GourmetShopRepositoryをシングルトンにする
    companion object Factory{
        val instance: GourmetShopRepository
        @Synchronized get(){
            return GourmetShopRepository()
        }
    }
}