package com.example.hotpeppergourmetsearch

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotpeppergourmetsearch.gourmetShop.GourmetShopViewModel
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData
import com.example.hotpeppergourmetsearch.inputSearchData.InputGPSActivity
import com.example.hotpeppergourmetsearch.inputSearchData.InputMealFeeActivity

/*
    検索結果画面
    検索データを用いて、ホットペッパーAPIから取得した店舗データをリストで表示する
 */
class SearchResultActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_DATA = "searchData"
        const val SHOP_DATA = "shopData"
    }

    private lateinit var searchData: SearchData     // 検索データ
    private val viewModel by viewModels<GourmetShopViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        // RecyclerViewの設定
        val searchResultList = findViewById<RecyclerView>(R.id.searchResultList)
        val adapter = SearchResultListAdapter(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        searchResultList.addItemDecoration(itemDecoration)
        searchResultList.adapter = adapter
        searchResultList.layoutManager = LinearLayoutManager(this) // 各画面部品を縦に並べる

        searchData = intent.getSerializableExtra(InputGPSActivity.SEARCH_DATA) as SearchData

        // ホットペッパーAPIから周辺の店舗データを取得する
        viewModel.getGourmetShop(searchData)

        // ホットペッパーAPIから受け取った店舗データをリストとして表示する
        viewModel.gourmetShopList.observe(this, {
            adapter.setShopList(it) // 店舗データをAdapterに設定する
            val listErrorView = findViewById<TextView>(R.id.listErrorView)
            // ホットペッパーAPIのレスポンスが0件の場合
            if (it.isEmpty()) {
                // エラーメッセージをTextViewに表示する
                listErrorView.text = "周辺にレストランがありません\n検索範囲を広げてください"
            } else {
                // TextViewに元から書いてあるエラーメッセージを消去する
                listErrorView.text = ""
            }
        })

        /*
            ListのItemがクリックされた時の処理
            選択された店舗データを店舗詳細画面に渡す
         */
        adapter.setOnClickListener {
            val intent = Intent(this, ShopDetailActivity::class.java)
            intent.putExtra(ShopDetailActivity.SHOP_DATA, it)
            intent.putExtra(ShopDetailActivity.SEARCH_DATA, searchData)
            startActivity(intent)
        }
    }

    /*
        Backキーを押した際の処理
        予算の選択を行う画面に画面遷移
     */
    override fun onBackPressed() {
        val intent = Intent(this, InputMealFeeActivity::class.java)
        intent.putExtra(InputMealFeeActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}