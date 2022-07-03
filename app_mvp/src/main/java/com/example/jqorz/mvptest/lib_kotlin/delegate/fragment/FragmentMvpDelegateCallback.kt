package com.jqorz.tallybook.base.mvp.delegate.fragment

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.delegate.BaseDelegateCallback
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 生命周期回调
 */
interface FragmentMvpDelegateCallback<P : MvpPresenter<V>?, V : MvpView> : BaseDelegateCallback<P, V>