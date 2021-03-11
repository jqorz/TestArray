package com.jqorz.test.networkstate

/**
 * 保存接受状态变化的方法对象
 */
interface NetworkMonitorCallback {
    fun onNetworkChange(networkState: NetworkState)
}