package com.jqorz.test.basemodule.activity.life

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
class LifeTest2Activity : BaseActivity() {
    override fun init() {
        findViewById<View>(R.id.btn_1).setOnClickListener {
            LifeTestActivity.start2(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Logg.i("onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Logg.i("onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logg.i("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Logg.i("onStart")
        super.onStart()
    }

    override fun onRestart() {
        Logg.i("onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Logg.i("onResume")
        super.onResume()
    }

    override fun onPause() {
        Logg.i("onPause")
        super.onPause()
    }

    override fun onStop() {
        Logg.i("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logg.i("onDestroy")
        super.onDestroy()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_common
    }



    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LifeTest2Activity::class.java)
//            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(starter)
        }
    }
}