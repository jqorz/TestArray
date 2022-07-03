package com.jqorz.tallybook.base.mvp.delegate.fragment

/**
 * 一个生命周期接口
 * 与Fragment生命周期一致，目的是为了通过Fragment的生命周期去控制是否要attachView：
 */
interface FragmentDelegate {
    fun onCreateView()
    fun onPause()
    fun onResume()
    fun onStop()
    fun onDestroyView()
}