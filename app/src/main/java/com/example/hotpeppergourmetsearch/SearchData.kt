package com.example.hotpeppergourmetsearch

import java.io.Serializable

// ホットペッパーAPIの検索クエリを保存するData class
data class SearchData(
        var feeCode1: String,
        var feeCode2: String,
        var range: Int,
        var key: String,
        var latitude: Double,
        var longitude: Double
): Serializable
