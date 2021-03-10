package com.jqorz.test.networkstate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import com.jqorz.common.Logg

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/4/1
 * 注意添加权限
<uses-permission android:name="android.net.conn.CONNECTIVITY_CHANGE" />
<uses-permission android:name="android.net.wifi.WIFI_STATE_CHANGED" />
<uses-permission android:name="android.net.wifi.STATE_CHANGE" />
 */
class NetBroadcastReceiver : BroadcastReceiver() {
    var mOnNetworkChangeListener: OnNetworkChangeListener? = null


    override fun onReceive(context: Context, intent: Intent) {
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            //获取联网状态的NetworkInfo对象
            val info =
                    intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.state && info.isAvailable) {
                    if (info.type == ConnectivityManager.TYPE_WIFI
                            || info.type == ConnectivityManager.TYPE_MOBILE
                    ) {
                        Logg.i(TAG, getConnectionType(info.type) + "连上")
                        if (mOnNetworkChangeListener != null) {
                            mOnNetworkChangeListener!!.onNetworkChange(true)
                        }
                    }
                } else {
                    Logg.i(TAG, getConnectionType(info.type) + "断开")
                    if (mOnNetworkChangeListener != null) {
                        mOnNetworkChangeListener!!.onNetworkChange(false)
                    }
                }
            }
        }
    }

    private fun getConnectionType(type: Int): String {
        var connType = ""
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据"
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络"
        }
        return connType
    }

    interface OnNetworkChangeListener {
        fun onNetworkChange(isConnect: Boolean)
    }

    companion object {
        private const val TAG = "NetBroadcastReceiver"

        @JvmStatic
        fun getIntentFilter(): IntentFilter {
            return IntentFilter().apply {
                addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
                addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
                addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            }
        }
    }
}