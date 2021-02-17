package com.example.hotpeppergourmetsearch

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotpeppergourmetsearch.gourmetShop.Shop

// RecyclerViewを表示するためのAdapter
class SearchResultListAdapter(
    context: Context,
) : RecyclerView.Adapter<SearchResultListAdapter.SearchResultListHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var shopList: List<Shop> = emptyList()  // 店舗リスト
    private var listener: ((Shop) -> Unit)? = null

    inner class SearchResultListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoSmall: ImageView = itemView.findViewById(R.id.photoSmall)  // 店舗トップ写真
        val shopName: TextView = itemView.findViewById(R.id.shopName)       // 店舗名 + ジャンル
        val budget: TextView = itemView.findViewById(R.id.budget)           // 予算
        val access: TextView = itemView.findViewById(R.id.access)           // アクセス
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultListHolder {
        val itemView = inflater.inflate(R.layout.item_search_result, parent, false)
        return SearchResultListHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchResultListHolder, position: Int) {
        val shop: Shop = shopList[position]
        // URLから店舗トップ写真を表示
        Glide.with(holder.photoSmall.context).load(shop.photo?.pc?.s).into(holder.photoSmall)
        holder.shopName.text = "${shop.name} (${shop.genre?.name})" // 店舗名 + ジャンル
        holder.budget.text = shop.budget?.name.toString()           // 予算
        holder.access.text = shop.mobile_access.toString()          // アクセス

        // ItemがクリックされたことをSearchResultActivityへ通知
        holder.itemView.setOnClickListener {
            listener?.invoke(shop)
        }
    }

    // 店舗リストの要素数を取得
    override fun getItemCount() = shopList.size

    // 店舗リストのセッター
    fun setShopList(shopList: List<Shop>) {
        this.shopList = shopList
        notifyDataSetChanged()
    }

    // Itemのリスナーの設定
    fun setOnClickListener(listener: (Shop) -> Unit) {
        this.listener = listener
    }
}
