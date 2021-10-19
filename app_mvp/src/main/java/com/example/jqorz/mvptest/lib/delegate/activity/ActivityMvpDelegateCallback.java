package com.example.jqorz.mvptest.lib.delegate.activity;


import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.delegate.BaseDelegateCallback;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;

/**
 * 生命周期回调
 */

public interface ActivityMvpDelegateCallback<P extends MvpPresenter<V>, V extends MvpView>
        extends BaseDelegateCallback<P, V> {

}
