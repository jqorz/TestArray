package com.jqorz.tallybook.base.mvp.delegate.activity

import com.jqorz.tallybook.base.mvp.MvpView
import com.jqorz.tallybook.base.mvp.delegate.BaseDelegateCallback
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 生命周期回调
 */
interface ActivityMvpDelegateCallback<P : MvpPresenter<V>?, V : MvpView> : BaseDelegateCallback<P, V>