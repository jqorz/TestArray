package com.jqorz.test.kotlin

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.jqorz.test.R
import com.jqorz.test.wifi.WifiInfoActivity


/**
 * @author jqorz
 * @since 2018/8/7
 */


class KtWifiInfoActivity : AppCompatActivity(), View.OnClickListener {
    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var mWifiManager: WifiManager? = null
    private var mConnectivityManager: ConnectivityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)
        initView()
    }


    private fun initView() {
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        findViewById<View>(R.id.btn1).setOnClickListener(this)
        findViewById<View>(R.id.btn2).setOnClickListener(this)
        mWifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        mConnectivityManager = this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> showBtn1()
            R.id.btn2 -> showBtn2()
        }
    }


    fun showBtn1() {
        try {
            //WiFi是否连接
            val wifiInfo = mConnectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val isWifiConn = wifiInfo.isConnected
            //手机网络是否连接
            val networkInfo = mConnectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            val isMobileConn = networkInfo.isConnected
            if (isWifiConn) {
                textView1!!.text = String.format("WIFI连接信息：名称：%s 信息：%s", wifiInfo.extraInfo, wifiInfo.toString())
            }
            if (isMobileConn) {
                textView1!!.text = (String.format("移动网络信息：%s%s", textView1!!.text, wifiInfo.extraInfo))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun showBtn2() {
        /*
     getBSSID() 获取BSSID,在手机WIFI中，就是MAC地址
     getSSID() 获取SSID
     getDetailedStateOf() 获取客户端的连通性
     getHiddenSSID() 获得SSID是否被隐藏
     getIpAddress() 获取IP地址
     getLinkSpeed() 获得连接的速度
     getMacAddress() 获得Mac地址
     getRssi() 获得802.11n网络的信号
     getSupplicanState() 返回具体客户端状态的信息
     */
        val mWifiInfo = mWifiManager!!.connectionInfo
        Log.i(TAG, mWifiInfo.bssid)//8c:a6:df:07:7e:90
        Log.i(TAG, mWifiInfo.ssid) //"LIAIDI"  名称
        Log.i(TAG, mWifiInfo.ipAddress.toString() + "M") //1694607552M
        Log.i(TAG, mWifiInfo.linkSpeed.toString() + "S")  //65S
        Log.i(TAG, mWifiInfo.ipAddress.toString() + "ADD")
        Log.i(TAG, mWifiInfo.rssi.toString() + "802.11n网络的信号")
        textView2!!.setText(String.format("WIFI信息：%s", mWifiInfo.toString()))
    }

    companion object {

        private val TAG = WifiInfoActivity::class.java!!.getName()
    }


}
