package com.jqorz.tallybook.base.mvp.delegate.fragment

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.delegate.BaseDelegateCallback
import com.jqorz.tallybook.base.mvp.delegate.MvpInternalDelegate
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 实现了FragmentDelegate，在生命周期里控制Presenter与MvpView：
 * 当Fragment执行到各个生命周期时，这里会执行对应的生命周期
 */
class FragmentDelegateImp<P : MvpPresenter<V>?, V : MvpView>(baseMvpDelegateCallback: BaseDelegateCallback<P, V>?) : FragmentDelegate {
    private val mvpInternalDelegate: MvpInternalDelegate<P, V>
    override fun onCreateView() {
        mvpInternalDelegate.createPresenter()
        mvpInternalDelegate.attachView()
    }

    override fun onPause() {}
    override fun onResume() {}
    override fun onStop() {}
    override fun onDestroyView() {
        mvpInternalDelegate.detachView()
    }

    //传入BaseDelegateCallback 去控制Presenter
    init {
        if (baseMvpDelegateCallback == null) throw NullPointerException("the baseMvpDelegateCallback in FragmentDelegate is null")
        mvpInternalDelegate = MvpInternalDelegate(baseMvpDelegateCallback)
    }
}