package com.jqorz.test.framework.networkstate

/**
 * @author  jqorz
 * @since  2021/3/10
 */
enum class NetworkState {
    WIFI,//Wi-Fi网络
    GPRS,//移动蜂窝网络
    NONE,//没有网络
    OTHER//未知网络，包括蓝牙、VPN、LoWPAN
}