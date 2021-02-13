package com.example.hotpeppergourmetsearch

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

// GPSを検索する検索条件入力画面
class InputGPSActivity : AppCompatActivity() {

    companion object{
        const val SEARCH_DATA = "searchData"
    }

    lateinit var searchData: SearchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_gps)

        searchData = intent.getSerializableExtra(InputLocationActivity.SEARCH_DATA) as SearchData

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 位置情報が更新された際のリスナオブジェクトを生成
        val locationListener = GPSLocationListener()
        // ACCESS_FINE_LOCATIONが許可されているか
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 許可を求めるダイアログを表示
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this@InputGPSActivity, permissions, 1000)
            return
        }
        // 位置情報の追跡を開始
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener)
    }

    // パーミッションダイアログに対する処理
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationListener = GPSLocationListener()
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                locationListener)
        }
    }

    private inner class GPSLocationListener: LocationListener {
        override fun onLocationChanged(location: Location) {
            searchData.latitude = location.latitude // 緯度の取得
            searchData.longitude = location.longitude // 経度の取得
            screenTransition()
        }
    }

    // 検索結果画面へ画面遷移
    private fun screenTransition(){
        val intent = Intent(this, InputGPSActivity::class.java)
        intent.putExtra(InputGPSActivity.SEARCH_DATA, searchData)
        startActivity(intent)
    }
}