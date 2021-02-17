package com.example.hotpeppergourmetsearch.inputSearchData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.hotpeppergourmetsearch.R
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData

/*
    検索条件入力画面
    APIキーを入力するを行う画面
    YahooAPIキーは電話番号の取得に必要
 */
class InputKeyActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_DATA = "searchData"
    }

    // 検索データ
    private val searchData = SearchData(
        "", "", 0, "", 0.0, 0.0, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_key)
    }

    // APIキーの保存（ボタンのクリック処理）
    fun onAPIKeyButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val hotPepperApiEdit = findViewById<EditText>(R.id.hotPepperApiEdit)
        val yahooApiEdit = findViewById<EditText>(R.id.yahooApiEdit)
        if (hotPepperApiEdit.text.isNotEmpty()) {
            searchData.key = hotPepperApiEdit.text.toString()   // ホットペッパーAPIキーを検索データに保存
            searchData.yahooKey = yahooApiEdit.text.toString()  // YahooAPIキーを検索データに保存
            screenTransition()
        } else {
            Toast.makeText(this, "APIキーが入力されていません", Toast.LENGTH_SHORT).show()
        }
    }

    // 予算の選択を行う画面へ画面遷移
    private fun screenTransition() {
        val intent = Intent(this, InputMealFeeActivity::class.java)
        intent.putExtra(InputMealFeeActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}