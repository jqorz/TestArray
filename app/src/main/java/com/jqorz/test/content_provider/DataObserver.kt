package com.jqorz.test.content_provider

import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.util.Log
import com.jqorz.common.contentprovider.ProviderManger
import com.jqorz.test.util.AppConfig

/**
 * @author  jqorz
 * @since  2021/1/6
 */
class DataObserver(handler: Handler) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.i("jqjq", "数据改变 $selfChange $uri")
        val find = ProviderManger(AppConfig.getApp().contentResolver).testFind()
        Log.i("jqjq", "数据改变 当前数据为$find")
    }
}