package com.example.jqorz.mvptest.lib.delegate.activity;


import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.delegate.BaseDelegateCallback;
import com.example.jqorz.mvptest.lib.delegate.MvpInternalDelegate;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;

/**
 * 实现了ActivityDelegate，在生命周期里控制Presenter与MvpView：
 * 当Activity执行到各个生命周期时，这里会执行对应的生命周期
 */

public class ActivityDelegateImp<P extends MvpPresenter<V>, V extends MvpView> implements ActivityDelegate {
    private final MvpInternalDelegate<P, V> mvpInternalDelegate;

    //传入BaseDelegateCallback 去控制Presenter
    public ActivityDelegateImp(BaseDelegateCallback<P, V> baseMvpDelegateCallback) {
        if (baseMvpDelegateCallback == null)
            throw new NullPointerException("the baseMvpDelegateCallback in ActivityDelegateImp is null");
        mvpInternalDelegate = new MvpInternalDelegate<>(baseMvpDelegateCallback);

    }

    @Override
    public void onCreate() {
        mvpInternalDelegate.createPresenter();
        mvpInternalDelegate.attachView();

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mvpInternalDelegate.detachView();
    }
}
