package com.jqorz.tallybook.base.mvp.presenters

import com.jqorz.tallybook.base.mvp.MvpView
import java.lang.ref.WeakReference

/**
 * 避免内存泄漏，使用弱引用，并在退出Activity时销毁持有的Activity
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {
    private var reference: WeakReference<V>? = null

    override fun attachView(view: V) {
        if (reference == null) reference = WeakReference(view) //将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏
    }

    override fun detachView() {
        reference?.clear()
        reference = null
    }

    open fun getMvpView(): V  {
        return if (isAttach) reference!!.get()!! else throw java.lang.NullPointerException("have you ever called attachView() in BasePresenter")
    }

    val isAttach: Boolean
        get() = reference?.get() != null
}