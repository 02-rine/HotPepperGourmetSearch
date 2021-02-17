package com.example.hotpeppergourmetsearch

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hotpeppergourmetsearch.databinding.ActivityShopDetailBinding
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData
import com.example.hotpeppergourmetsearch.gourmetShop.Shop
import com.example.hotpeppergourmetsearch.inputSearchData.InputGPSActivity
import com.example.hotpeppergourmetsearch.yahooTel.TelViewModel
import java.net.URLEncoder

/*
    店舗詳細画面
    選択された店舗データの詳細を表示する
    YahooAPIキーが間違っている場合、電話番号は表示されない
 */
class ShopDetailActivity : AppCompatActivity() {

    companion object {
        const val SHOP_DATA = "shopData"
        const val SEARCH_DATA = "searchData"
    }

    private lateinit var shop: Shop                 // 店舗データ
    private lateinit var searchData: SearchData     // 検索データ

    private val viewModel by viewModels<TelViewModel>()
    private lateinit var binding: ActivityShopDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        shop = intent.getSerializableExtra(SearchResultActivity.SHOP_DATA) as Shop
        searchData = intent.getSerializableExtra(InputGPSActivity.SEARCH_DATA) as SearchData

        // DataBindingの設定
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // YahooAPIから店舗名に一致する電話番号を取得する
        viewModel.getTel(searchData.yahooKey, shop)
    }

    // 地図検索ボタンをクリック時、地図アプリを起動する
    fun onMapSearchButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        // 住所をURLエンコードする
        val searchAddress = URLEncoder.encode(shop.address, "UTF-8")
        // 地図アプリと連携するURI文字列を生成
        val uriStr = "geo:0:0?q=${searchAddress}"
        val uri = Uri.parse(uriStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

// ImageViewにURLから画像を表示する
@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions().override(300, 300))
        .apply(RequestOptions().error(R.drawable.ic_baseline_photo_size_select_actual_24))
        .into(imageView)
}