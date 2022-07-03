package com.jqorz.tallybook.base.mvp.delegate

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 用于对获取MvpView、创建以及获取Presenter
 */
interface BaseDelegateCallback<P : MvpPresenter<V>?, V : MvpView> {
    fun setPresenter()
    fun getPresenter(): P?
    fun createPresenter(): P
    fun getMvpView(): V
}