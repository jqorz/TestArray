package com.jqorz.test.networkstate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jqorz.common.Logg
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R

class NetworkStateActivity : BaseActivity(), NetworkMonitorCallback {
    override fun init() {
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Logg.i("广播接收 ${intent?.action}")
            }
        }, NetBroadcastReceiver.getIntentFilter())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NetworkMonitorManager.init(this)
        NetworkMonitorManager.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkMonitorManager.destroy()
        NetworkMonitorManager.unregister(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_network_state
    }


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, NetworkStateActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onNetworkChange(networkState: NetworkState) {
        Logg.i("回调接收 ${networkState}")
    }
}