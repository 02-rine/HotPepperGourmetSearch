package com.example.hotpeppergourmetsearch.gourmetShop

import java.io.Serializable

data class GourmetShop(
    val results: Result
)

data class Result(
    val shop: List<Shop>
)

data class Shop(
    val name: String?,          // 店舗名称
    val mobile_access: String?, // 交通アクセス
    val photo: Photo?,          // 写真
    val budget: Budget?,        // 予算
    val genre: Genre?,          // ジャンル

    // 店舗詳細画面のみ使用する
    val address: String?,       // 住所
    val open: String?,          // 営業時間
    val catch: String?,         // キャッチ（魅力）
    val station_name: String?   // 最寄り駅
): Serializable

data class Photo(
    val pc: PC?                 // PC向けの写真
): Serializable

data class PC(
    val l: String?,             // 店舗トップ写真（大）URL
    val m: String,              // 店舗トップ写真（中）URL
    val s: String?              // 店舗トップ写真（小）URL
): Serializable

data class Budget(
    val name: String?           // 予算
): Serializable

data class Genre(
    val name: String?,          // ジャンル
): Serializable
