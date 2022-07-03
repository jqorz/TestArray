package com.jqorz.tallybook.base.mvp

import android.os.Bundle
import com.jqorz.tallybook.base.ToolBaseActivity
import com.jqorz.tallybook.base.mvp.delegate.activity.ActivityDelegate
import com.jqorz.tallybook.base.mvp.delegate.activity.ActivityDelegateImp
import com.jqorz.tallybook.base.mvp.delegate.activity.ActivityMvpDelegateCallback
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 基类Activity
 */
abstract class BaseMvpActivity<P : MvpPresenter<V>?, V : MvpView> : ToolBaseActivity(), ActivityMvpDelegateCallback<P, V>, MvpView {
    private lateinit var mActivityDelegate: ActivityDelegate
    private var mPresenter: P? = null

    final override fun initView() {
        //do nothing
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDelegate = ActivityDelegateImp(this).apply {
            onCreate()
        }
        initView0()
    }

    abstract fun initView0()

    override fun onResume() {
        super.onResume()
        mActivityDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        mActivityDelegate.onPause()
    }

    override fun onStop() {
        super.onStop()
        mActivityDelegate.onStop()
    }

    override fun setPresenter() {}

    //暴露一个创建的方法用于创建presenter
    protected abstract fun createThePresenter(): P

    //这个方法由MvpInternalDelegate 调用 BaseDelegateCallback 来创建Presenter
    override fun createPresenter(): P {
        mPresenter = this.createThePresenter()
        return mPresenter!!
    }

    override fun getPresenter(): P? {
        return mPresenter
    }

    override fun getMvpView(): V {
        return this as V
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivityDelegate.onDestroy()
    }


}