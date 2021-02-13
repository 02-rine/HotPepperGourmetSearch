package com.example.hotpeppergourmetsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

// 検索範囲を入力する検索条件入力画面
class InputLocationActivity : AppCompatActivity() {

    companion object{
        const val SEARCH_DATA = "searchData"
    }

    lateinit var searchData: SearchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_location)
        searchData = intent.getSerializableExtra(InputLocationActivity.SEARCH_DATA) as SearchData
    }

    // 検索範囲 ~300m（ボタンクリック処理）
    fun onRadius300mButtonClick(view: View){
        searchData.range = 1
        screenTransition()
    }

    // 検索範囲 ~500m
    fun onRadius500mButtonClick(view: View){
        searchData.range = 2
        screenTransition()
    }

    // 検索範囲 ~1000m
    fun onRadius1000mButtonClick(view: View){
        searchData.range = 3
        screenTransition()
    }

    // 検索範囲 ~2000m
    fun onRadius2000mButtonClick(view: View){
        searchData.range = 4
        screenTransition()
    }

    // 検索範囲 ~3000m
    fun onRadius3000mButtonClick(view: View){
        searchData.range = 5
        screenTransition()
    }

    // GPSを検索する検索条件入力画面へ画面遷移
    private fun screenTransition(){
        val intent = Intent(this, InputGPSActivity::class.java)
        intent.putExtra(InputGPSActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}