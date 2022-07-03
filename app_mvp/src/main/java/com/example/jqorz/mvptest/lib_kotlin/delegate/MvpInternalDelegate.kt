package com.jqorz.tallybook.base.mvp.delegate

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 用于控制MvpPresenter与MvpView:
 */
class MvpInternalDelegate<P : MvpPresenter<V>?, V : MvpView>(var callback: BaseDelegateCallback<P, V>) {
    fun createPresenter(): P {
        var p = callback.getPresenter()
        if (p == null) p = callback.createPresenter()
        if (p == null) throw NullPointerException("callback.createPresenter() is null in MvpInternalDelegate")
        return p
    }

    fun attachView() {
        callback.getPresenter()?.attachView(callback.getMvpView())
    }

    fun detachView() {
        callback.getPresenter()?.detachView()
    }
}