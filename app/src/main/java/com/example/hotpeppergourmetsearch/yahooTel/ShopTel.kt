package com.example.hotpeppergourmetsearch.yahooTel

/*
    YahooAPIから受け取るデータの設定を行う
 */
data class ShopTel(
        val Feature: List<Feature>?,
)

data class Feature(
        val Property: Property?,
        val Name: String?,   // 店舗名
)

data class Property(
        val Tel1: String?,   // 電話番号
)