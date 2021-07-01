package com.jqorz.test.content_provider

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import com.jqorz.common.base.BaseActivity
import com.jqorz.common.contentprovider.ProviderConstant
import com.jqorz.common.contentprovider.ProviderManger
import com.jqorz.test.R
import com.jqorz.test.util.AppConfig

/**
 * @author  jqorz
 * @since  2020/12/29
 */
class ContentProviderActivity : BaseActivity() {

    private var observer: DataObserver? = null

    override fun init() {
        observer = DataObserver(Handler())
        observer?.let {
            contentResolver.registerContentObserver(ProviderConstant.CONTENT_URI, true, it)
        }

        findViewById<View>(R.id.btn_insert).setOnClickListener {
            ProviderManger(AppConfig.getApp().contentResolver).testInsert()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        observer?.let {
            contentResolver.unregisterContentObserver(it)
        }
    }

    override fun getLayoutResId() = R.layout.activity_content_provider

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ContentProviderActivity::class.java)
            context.startActivity(starter)
        }
    }
}