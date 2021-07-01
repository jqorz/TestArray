package com.jqorz.test

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.didichuxing.doraemonkit.DoraemonKit
import com.jqorz.test.coil.CustomManager
import com.jqorz.test.mac.DeviceUtils
import com.jqorz.test.util.AppConfig
import com.jqorz.test.util.ToolUtil

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
class TestApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        DoraemonKit.install(this)
        AppConfig.init(this)
        ToolUtil.init(this)
        val mac = DeviceUtils.getMacAddress()
        Log.e("mac1 ", mac)
        Log.e("启动", "Application1")
    }

    override fun newImageLoader(): ImageLoader {
        return CustomManager.createProgressLoader(this)
    }

}