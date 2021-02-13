package com.example.hotpeppergourmetsearch

import java.io.Serializable

// ホットペッパーAPIの検索クエリを保存するData class
data class SearchData(
        var feeCode1: String, // 予算コード１
        var feeCode2: String, // 予算コード２
        var range: Int, // 検索範囲
        var key: String, // APIキー
        var latitude: Double, // 緯度
        var longitude: Double // 経度
): Serializable
