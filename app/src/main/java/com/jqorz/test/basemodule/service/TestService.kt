package com.jqorz.test.basemodule.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.jqorz.common.Logg

/**
 * @author jqorz
 * @since 2021/8/27
 */
class TestService : Service() {

    private var binder: MyBinder? = null

    inner class MyBinder : Binder() {
        fun getService(): TestService {
            return this@TestService
        }
    }

    fun getHello(): String {
        return "hello"
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
        Logg.i("onBind")
        return binder
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