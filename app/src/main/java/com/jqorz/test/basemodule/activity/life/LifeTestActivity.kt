package com.jqorz.test.basemodule.activity.life

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jqorz.common.Logg
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R


/**
 * @author  jqorz
 * @since  2021/8/27
 */
class LifeTestActivity : BaseActivity() {
    override fun init() {
        findViewById<View>(R.id.btn_1).setOnClickListener {
            LifeTest2Activity.start(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Logg.i("jqjq1", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Logg.i("jqjq1", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logg.i("jqjq1", "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Logg.i("jqjq1", "onStart")
        super.onStart()
    }

    override fun onRestart() {
        Logg.i("jqjq1", "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Logg.i("jqjq1", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Logg.i("jqjq1", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Logg.i("jqjq1", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logg.i("jqjq1", "onDestroy")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        Logg.i("jqjq1", "onNewIntent")
        super.onNewIntent(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logg.i("jqjq1", "onActivityResult resultCode=${resultCode}")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_common
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LifeTestActivity::class.java)
            context.startActivity(starter)
        }

        fun start2(context: Context) {
            val starter = Intent(context, LifeTestActivity::class.java)
            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            context.startActivity(starter)
        }
    }
}