package com.jqorz.test.basemodule.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.jqorz.test.util.ToastUtil

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

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onDestroy() {
        ToastUtil.showToast("销毁了")
        super.onDestroy()
    }

}