package com.jqorz.test.basemodule.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils

/**
 * 测试广播接收器用法
 */
class MyReceiver : BroadcastReceiver() {
    var onReceiveBroadcastListener: com.jqorz.test.basemodule.broadcast.MyReceiver.OnReceiveBroadcastListener? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (TextUtils.equals(intent.action, com.jqorz.test.basemodule.broadcast.SendBroadActivity.Companion.INTENT_ACTION)) {
            onReceiveBroadcastListener?.onChanged(intent.getStringExtra("data"))
        }
    }

    interface OnReceiveBroadcastListener {
        fun onChanged(text: String?)
    }
}