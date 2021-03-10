package com.jqorz.test.networkstate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jqorz.common.Logg
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R

class NetworkStateActivity : BaseActivity() {
    override fun init() {
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Logg.i("${intent?.action}")
            }
        }, NetBroadcastReceiver.getIntentFilter())

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
}