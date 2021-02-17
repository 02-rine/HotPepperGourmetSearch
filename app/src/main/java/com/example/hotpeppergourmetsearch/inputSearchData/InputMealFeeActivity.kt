package com.example.hotpeppergourmetsearch.inputSearchData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hotpeppergourmetsearch.R
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData

/*
    検索条件入力画面
    予算の選択を行う画面
 */
class InputMealFeeActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_DATA = "searchData"
    }

    private lateinit var searchData: SearchData     // 検索データ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_meal_fee)
        searchData = intent.getSerializableExtra(InputKeyActivity.SEARCH_DATA) as SearchData
    }

    // ディナー予算 500~1000円（ボタンのクリック処理）
    fun onFee500ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B010" // 500~1000円
        searchData.feeCode2 = ""
        screenTransition()
    }

    // ディナー予算 1001~2000円
    fun onFee1000ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B011" // 1001~1500円
        searchData.feeCode2 = "B001" // 1501~2000円
        screenTransition()
    }

    // ディナー予算 2001~3000円
    fun onFee2000ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B002" // 2001~3000円
        searchData.feeCode2 = ""
        screenTransition()
    }

    // ディナー予算 3001~5000円
    fun onFee3000ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B003" // 3001~4000円
        searchData.feeCode2 = "B008" // 4001~5000円
        screenTransition()
    }

    // ディナー予算 5001~10000円
    fun onFee5000ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B004" // 5001~7000円
        searchData.feeCode2 = "B005" // 7001~10000円
        screenTransition()
    }

    // ディナー予算 10001~20001円
    fun onFee10000ButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        searchData.feeCode1 = "B006" // 10001~15000円
        searchData.feeCode2 = "B012" // 15001~20000円
        screenTransition()
    }

    // 検索範囲の選択を行う画面へ画面遷移
    private fun screenTransition() {
        val intent = Intent(this, InputLocationActivity::class.java)
        intent.putExtra(InputLocationActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }

    /*
        Backキーを押した際の処理
        APIキーの入力を行う画面へ画面遷移
     */
    override fun onBackPressed() {
        val intent = Intent(this, InputKeyActivity::class.java)
        startActivity(intent)
    }
}