package com.example.hotpeppergourmetsearch.gourmetShop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/*
    ホットペッパーAPIから受け取った店舗データを管理する
 */
class GourmetShopViewModel : ViewModel() {
    private val repository = GourmetShopRepository.instance

    // 店舗のリスト
    private val _gourmetShopList = MutableLiveData<List<Shop>>()
    val gourmetShopList: LiveData<List<Shop>> = _gourmetShopList

    // 検索データを用いて、ホットペッパーAPIから周辺の店舗データを取得する
    fun getGourmetShop(searchData: SearchData) {
        viewModelScope.launch {
            val response = repository.getGourmetShop(
                searchData.feeCode1,            // 予算コード1
                searchData.feeCode2,            // 予算コード2
                searchData.range.toString(),    // 検索範囲
                searchData.key,                 // ホットペッパーAPIキー
                searchData.latitude.toString(), // 緯度
                searchData.longitude.toString() // 経度
            )
            if (response.isSuccessful) {
                val shopList = response.body()?.results?.shop
                if (shopList != null) {
                    // LiveDataのobserveを用いて、SearchResultActivityで画面を更新する
                    _gourmetShopList.postValue(shopList)
                }
            }
        }
    }
}