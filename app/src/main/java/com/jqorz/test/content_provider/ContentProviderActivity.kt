package com.jqorz.test.content_provider

import android.content.Context
import android.content.Intent
import android.os.Handler
import com.jqorz.common.contentprovider.ProviderConstant
import com.jqorz.test.R
import com.jqorz.test.base.BaseActivity

/**
 * @author  jqorz
 * @since  2020/12/29
 */
class ContentProviderActivity : BaseActivity() {

    private var observer: DataObserver? = null

    override fun init() {
        observer = DataObserver(Handler())
        observer.let {
            contentResolver.registerContentObserver(ProviderConstant.CONTENT_URI, true, it)
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