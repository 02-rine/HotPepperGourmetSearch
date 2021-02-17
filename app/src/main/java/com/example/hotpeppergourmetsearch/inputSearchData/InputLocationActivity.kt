package com.example.hotpeppergourmetsearch.inputSearchData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hotpeppergourmetsearch.R
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData

/*
    検索条件入力画面
    検索範囲の選択を行う画面
 */
class InputLocationActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_DATA = "searchData"
    }

    private lateinit var searchData: SearchData     // 検索データ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_location)
        searchData = intent.getSerializableExtra(InputMealFeeActivity.SEARCH_DATA) as SearchData
    }

    // 検索範囲 ~300m（ボタンクリック処理）
    fun onRadius300mButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.range = 1    // 検索範囲を検索データに格納
        screenTransition()
    }

    // 検索範囲 ~500m
    fun onRadius500mButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.range = 2
        screenTransition()
    }

    // 検索範囲 ~1000m
    fun onRadius1000mButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.range = 3
        screenTransition()
    }

    // 検索範囲 ~2000m
    fun onRadius2000mButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.range = 4
        screenTransition()
    }

    // 検索範囲 ~3000m
    fun onRadius3000mButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.range = 5
        screenTransition()
    }

    // GPSの検索を行う画面へ画面遷移
    private fun screenTransition() {
        val intent = Intent(this, InputGPSActivity::class.java)
        intent.putExtra(InputGPSActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}