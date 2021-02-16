package com.example.hotpeppergourmetsearch.gourmetShop

import java.io.Serializable

// ホットペッパーAPIの検索クエリを保存するData class
data class SearchData(
        var feeCode1: String,   // 予算コード1
        var feeCode2: String,   // 予算コード2
        var range: Int,         // 検索範囲
        var key: String,        // APIキー
        var latitude: Double,   // 緯度
        var longitude: Double,  // 経度
        var yahooKey: String    // YahooAPIキー
): Serializable