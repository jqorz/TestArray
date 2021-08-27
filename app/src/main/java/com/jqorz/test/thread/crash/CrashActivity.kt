package com.jqorz.test.thread.crash

import android.content.Context
import android.content.Intent
import android.util.Log
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R


/**
 * @author  jqorz
 * @since  2021/3/3
 */
class CrashActivity : BaseActivity() {
    override fun init() {
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("jqjq1", "error=${e.localizedMessage}")
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("jqjq2", "error=${e.localizedMessage}")
        }
        throw Exception("test")
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_default
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CrashActivity::class.java)
            context.startActivity(starter)
        }
    }
}