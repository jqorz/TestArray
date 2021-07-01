package com.jqorz.test2

import android.app.Application
import android.util.Log

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
class Test2Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.e("启动", "Application2")
    }


}