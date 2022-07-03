package com.jqorz.tallybook.base.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jqorz.tallybook.base.mvp.delegate.fragment.FragmentDelegate
import com.jqorz.tallybook.base.mvp.delegate.fragment.FragmentDelegateImp
import com.jqorz.tallybook.base.mvp.delegate.fragment.FragmentMvpDelegateCallback
import com.jqorz.tallybook.base.mvp.presenters.MvpPresenter

/**
 * 使用MVP的Fragment
 */
abstract class BaseMvpFragment<P : MvpPresenter<V>?, V : MvpView> : Fragment(), FragmentMvpDelegateCallback<P, V>, MvpView {
    private lateinit var mFragmentDelegate: FragmentDelegate
    private var mPresenter: P? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentDelegate = FragmentDelegateImp(this).apply {
            onCreateView()
        }
        val view = inflater.inflate(layoutResId, container, false)
        init()
        return view
    }

    override fun onPause() {
        super.onPause()
        mFragmentDelegate.onPause()
    }

    override fun onResume() {
        super.onResume()
        mFragmentDelegate.onResume()
    }

    override fun onStop() {
        super.onStop()
        mFragmentDelegate.onStop()
    }

    protected abstract fun init()
    protected abstract val layoutResId: Int
    override fun onDestroyView() {
        super.onDestroyView()
        mFragmentDelegate.onDestroyView()
    }

    override fun setPresenter() {}

    //暴露一个创建的方法用于创建presenter
    protected abstract fun createThePresenter(): P

    //这个方法由MvpInternalDelegate 调用BaseDelegateCallback 来创建presenter
    override fun createPresenter(): P {
        mPresenter = createThePresenter()
        return mPresenter!!
    }

    override fun getPresenter(): P {
        return mPresenter!!
    }

    override fun getMvpView(): V {
        return this as V
    }
}