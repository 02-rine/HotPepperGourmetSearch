package com.example.hotpeppergourmetsearch.yahooTel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotpeppergourmetsearch.gourmetShop.Shop
import kotlinx.coroutines.launch

/*
    YahooAPIから受け取った電話番号と店舗名を管理する
 */
class TelViewModel : ViewModel() {
    private val repository = TelRepository.instance

    // 店舗データ
    private val _shop = MutableLiveData<Shop>()
    val shop: LiveData<Shop> = _shop

    // 電話番号
    private val _tel = MutableLiveData<String>()
    val tel: LiveData<String> = _tel

    init {
        _tel.postValue("")
    }

    // 店舗名から電話番号を取得する
    fun getTel(appid: String, shop: Shop) {
        _shop.postValue(shop)
        val query: String? = shop.name  // 店舗データから店舗名を取得し、格納する
        query?.let {
            viewModelScope.launch {
                val response = repository.getTel(
                    appid,  // YahooAPIキー
                    query   // 検索する単語（店舗名）
                )
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        val telResult = searchTel(it, shop.name)
                        _tel.postValue(telResult)
                    }
                }
            }
        }
    }

    /*
        YahooAPIから店舗名を検索した場合、店舗名に似ている店舗名の電話番号を返す可能性がある
        そのため、検索する店舗名と、レスポンスとしてYahooAPIから取得した店舗名が一致するか調べる
        一致したらそのデータの電話番号を返す
     */
    private fun searchTel(tel: ShopTel, shopName: String): String? {
        tel.Feature?.let {
            for (feature in tel.Feature) {
                if (feature.Name.equals(shopName)) {
                    // 一致した場合はその電話番号を返す
                    return feature.Property?.Tel1
                }
            }
        }
        // 一致しない場合は空文字を返す
        return ""
    }
}