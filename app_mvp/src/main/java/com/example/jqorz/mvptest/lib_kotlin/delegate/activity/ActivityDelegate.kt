package com.jqorz.tallybook.base.mvp.delegate.activity

/**
 * 一个生命周期接口
 * 与activity生命周期一致，目的是为了通过activity的生命周期去控制是否要attachView：
 */
interface ActivityDelegate {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onStop()
    fun onDestroy()
}