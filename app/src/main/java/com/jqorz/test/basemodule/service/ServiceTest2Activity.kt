package com.jqorz.test.basemodule.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R
import com.jqorz.test.util.ToastUtil

/**
 * @author jqorz
 * @since 2021/8/27
 */
class ServiceTest2Activity : BaseActivity() {


    override fun init() {

    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, TestService::class.java)
        bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val s = (service as TestService.MyBinder).getService().getHello()
                ToastUtil.showToast(s)
            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }

        }, BIND_AUTO_CREATE)

    }

    override fun getLayoutResId() = R.layout.activity_common

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ServiceTest2Activity::class.java)
            context.startActivity(starter)
        }
    }
}