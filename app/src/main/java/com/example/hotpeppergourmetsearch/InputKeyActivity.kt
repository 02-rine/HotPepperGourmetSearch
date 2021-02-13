package com.example.hotpeppergourmetsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

// APIキーを入力する検索条件入力画面
class InputKeyActivity : AppCompatActivity() {

    // 検索用の単語を保存する変数
    private val searchData = SearchData("", "", 0, "", 0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_key)
    }

    // APIキーの保存（ボタンのクリック処理）
    fun onAPIKeyButtonClick(view: View) {
        val apiKeyEditText = findViewById<EditText>(R.id.apiKeyEditText)
        if (apiKeyEditText.text.isNotEmpty()) {
            searchData.key = apiKeyEditText.text.toString() // APIキーを保存
            screenTransition()
        } else {
            Log.i("test1", "1")
            Toast.makeText(this, "APIキーが入力されていません", Toast.LENGTH_SHORT).show()
        }
    }

    // ディナー料金を入力する検索条件入力画面へ画面遷移
    private fun screenTransition(){
        val intent = Intent(this, InputMealFeeActivity::class.java)
        intent.putExtra(InputMealFeeActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}