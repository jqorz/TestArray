package com.jqorz.tallybook.base.mvp.delegate.activity

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.delegate.BaseDelegateCallback
import com.jqorz.tallybook.base.mvp.delegate.MvpInternalDelegate
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 实现了ActivityDelegate，在生命周期里控制Presenter与MvpView：
 * 当Activity执行到各个生命周期时，这里会执行对应的生命周期
 */
class ActivityDelegateImp<P : MvpPresenter<V>?, V : MvpView>(baseMvpDelegateCallback: BaseDelegateCallback<P, V>?) : ActivityDelegate {
    private val mvpInternalDelegate: MvpInternalDelegate<P, V>
    override fun onCreate() {
        mvpInternalDelegate.createPresenter()
        mvpInternalDelegate.attachView()
    }

    override fun onPause() {}
    override fun onResume() {}
    override fun onStop() {}
    override fun onDestroy() {
        mvpInternalDelegate.detachView()
    }

    //传入BaseDelegateCallback 去控制Presenter
    init {
        if (baseMvpDelegateCallback == null) throw NullPointerException("the baseMvpDelegateCallback in ActivityDelegateImp is null")
        mvpInternalDelegate = MvpInternalDelegate(baseMvpDelegateCallback)
    }
}