package com.jqorz.tallybook.base.mvp.presenters

import com.jqorz.tallybook.base.mvp.MvpView

/**
 * Presenter的基础类，控制的MvpView的子类
 */
interface MvpPresenter<V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}