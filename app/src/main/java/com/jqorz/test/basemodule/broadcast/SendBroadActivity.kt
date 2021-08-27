package com.jqorz.test.basemodule.broadcast

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R
import com.jqorz.test.util.ToastUtil

class SendBroadActivity : BaseActivity() {
    override fun init() {
        val myReceiver = MyReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(INTENT_ACTION)
        registerReceiver(myReceiver, intentFilter)
        myReceiver.onReceiveBroadcastListener = object :
            MyReceiver.OnReceiveBroadcastListener {
            override fun onChanged(text: String?) {
                ToastUtil.showToast(text)
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_broadcast
    }

    fun sendBroad(v: View?) {
        val intent = Intent()
        intent.action = INTENT_ACTION
        intent.putExtra("data", "发送一个广播")
        sendBroadcast(intent)
    }

    companion object {
        const val INTENT_ACTION = "INTENT_ACTION"

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SendBroadActivity::class.java)
            context.startActivity(starter)
        }
    }
}