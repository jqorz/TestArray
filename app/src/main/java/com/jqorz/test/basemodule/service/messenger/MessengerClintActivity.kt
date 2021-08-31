package com.jqorz.test.basemodule.service.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R


/**
 * @author  jqorz
 * @since  2021/8/28
 */
class MessengerClintActivity : BaseActivity() {

    private var messenger: Messenger? = null

    private var connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messenger = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messenger = null
        }
    }


    override fun init() {
        findViewById<View>(R.id.btn_1).setOnClickListener {
            messenger?.send(Message.obtain().apply {
                what = 123
            })
        }
    }

    override fun onStart() {
        super.onStart()
        bindService()
    }

    override fun onStop() {
        super.onStop()
        unbindService()
    }

    private fun bindService() {
        val intent = Intent()
//        intent.component = ComponentName(this, Messenger1Service::class.java)
        intent.component = ComponentName(this, "com.jqorz.test.basemodule.service.messenger.Messenger1Service")
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    private fun bindService2() {
        val intent = Intent()
        intent.component = ComponentName("con.jqorz.test2", "com.jqorz.test2.messenger.Messenger2Service")
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        unbindService(connection)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_common
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MessengerClintActivity::class.java)
            context.startActivity(starter)
        }
    }
}