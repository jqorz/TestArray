package com.jqorz.test.basemodule.service.messenger

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import com.jqorz.common.Logg

/**
 * @author jqorz
 * @since 2021/8/27
 */
class Messenger1Service : Service() {

    internal class MyHandler(val context: Context) : Handler() {
        override fun handleMessage(msg: Message) {
            Logg.i("服务端收到客服端消息")
            msg.replyTo.send(Message.obtain().apply {
                what = 321
            })
            Toast.makeText(context, "${msg.what}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logg.i("onStartCommand")
        return START_STICKY
    }

    override fun onCreate() {
        Logg.i("onCreate")
        super.onCreate()
    }


    override fun onBind(intent: Intent?): IBinder? {
        val messenger = Messenger(MyHandler(this))
        return messenger.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logg.i("onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Logg.i("onDestroy")
        super.onDestroy()
    }

}