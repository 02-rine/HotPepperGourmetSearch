package com.example.hotpeppergourmetsearch.inputSearchData

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.hotpeppergourmetsearch.R
import com.example.hotpeppergourmetsearch.gourmetShop.SearchData
import com.example.hotpeppergourmetsearch.SearchResultActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/*
    検索条件入力画面
    GPSの検索を行う画面
 */
class InputGPSActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_DATA = "searchData"
    }

    private lateinit var searchData: SearchData     // 検索データ
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_gps)

        searchData = intent.getSerializableExtra(InputLocationActivity.SEARCH_DATA) as SearchData

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // ACCESS_FINE_LOCATIONを許可した場合
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // ACCESS_FINE_LOCATIONの許可を求めるダイアログの生成
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this@InputGPSActivity, permissions, 1000)
            return
        }

        // GPSから位置情報を取得
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                searchData.latitude = location.latitude     // 緯度を検索データに格納
                searchData.longitude = location.longitude   // 経度を検索データに格納
                screenTransition()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // パーミッションダイアログで許可を選択した場合
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            if (ActivityCompat.checkSelfPermission(applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
        }

        // GPSから位置情報を取得
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                searchData.latitude = location.latitude     // 緯度を検索データに格納
                searchData.longitude = location.longitude   // 経度を検索データに格納
                screenTransition()
            }
        }
    }

    // 検索結果画面へ画面遷移
    private fun screenTransition() {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra(SearchResultActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}