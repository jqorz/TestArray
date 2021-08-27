package com.jqorz.test.basemodule.service

import android.content.Context
import android.content.Intent
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.test.R

/**
 * @author jqorz
 * @since 2021/8/27
 */
class ServiceTestActivity : BaseActivity() {
    override fun init() {

    }

    override fun getLayoutResId() = R.layout.activity_service


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ServiceTestActivity::class.java)
            context.startActivity(starter)
        }
    }

    fun onClickBtn1(view: View) {
        ServiceTest2Activity.start(this)
    }

    fun onClickBtn2(view: View) {
    }

}