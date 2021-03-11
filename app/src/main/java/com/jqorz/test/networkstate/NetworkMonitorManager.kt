package com.jqorz.test.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build


object NetworkMonitorManager {

    private var networkCallbackList: MutableSet<NetworkMonitorCallback>? = mutableSetOf()
    private var connectivityManager: ConnectivityManager? = null

    private var networkCallback: ConnectivityManager.NetworkCallback =
            object : ConnectivityManager.NetworkCallback() {
                /**
                 * 网络可用的回调连接成功
                 */
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    val networkState: NetworkState =
                            when (getConnectedType(connectivityManager)) {
                                -1 -> NetworkState.NONE
                                ConnectivityManager.TYPE_WIFI -> NetworkState.WIFI
                                ConnectivityManager.TYPE_MOBILE -> NetworkState.GPRS
                                else -> NetworkState.OTHER
                            }
                    postNetState(networkState)
                }

                /**
                 * 网络不可用时调用和onAvailable成对出现
                 */
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postNetState(NetworkState.NONE)
                }
            }

    /**
     * 初始化 传入application
     * 应用全局初始化一次即可
     *
     */
    fun init(context: Context?) {
        if (context == null) {
            throw NullPointerException("application can not be null")
        }
        connectivityManager =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> { //API 大于26时
                connectivityManager?.registerDefaultNetworkCallback(networkCallback)
            }
            else -> { //API 大于21时
                connectivityManager?.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
            }
        }
    }

    /**
     * 如果是全局初始化，不用销毁
     */
    fun destroy() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }


    fun register(callback: NetworkMonitorCallback) {
        networkCallbackList?.add(callback)
    }


    fun unregister(callback: NetworkMonitorCallback) {
        networkCallbackList?.remove(callback)
    }

    fun getConnectedType(mConnectivityManager: ConnectivityManager?): Int {
        val mNetworkInfo = mConnectivityManager?.activeNetworkInfo
        if (mNetworkInfo?.isAvailable == true) {
            return mNetworkInfo.type
        }
        return -1
    }

    /**
     * 网络状态发生变化，需要去通知更改
     * @param networkState
     */
    private fun postNetState(networkState: NetworkState) {
        val iterator = networkCallbackList?.iterator()
        while (iterator?.hasNext() == true) {
            val it = iterator.next()
            it.onNetworkChange(networkState)
        }
    }


}