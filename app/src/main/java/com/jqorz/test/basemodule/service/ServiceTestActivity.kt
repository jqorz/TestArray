package com.jqorz.test.basemodule.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R
import com.jqorz.test.util.ToastUtil

/**
 * @author jqorz
 * @since 2021/8/27
 */
class ServiceTestActivity : BaseActivity() {

    private var connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val s = (service as TestService.MyBinder).getService().getHello()
            ToastUtil.showToast(s)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }

    override fun init() {

    }


    override fun getLayoutResId() = R.layout.activity_service_manager

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ServiceTestActivity::class.java)
            context.startActivity(starter)
        }
    }

    fun bind(view: View) {
        val intent = Intent(this, TestService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    fun unbind(view: View) {
        unbindService(connection)
    }

    fun start(view: View) {
        val intent = Intent(this, TestService::class.java)
        startService(intent)
    }

    fun stop(view: View) {
        val intent = Intent(this, TestService::class.java)
        stopService(intent)
    }
}