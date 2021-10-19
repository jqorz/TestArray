package com.example.jqorz.mvptest.lib.delegate;


import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;

/**
 * 用于控制MvpPresenter与MvpView:
 */

public class MvpInternalDelegate<P extends MvpPresenter<V>, V extends MvpView> {
    BaseDelegateCallback<P, V> callback;

    public MvpInternalDelegate(BaseDelegateCallback<P, V> callback) {
        this.callback = callback;
    }


    public P createPresenter() {
        P p = callback.getPresenter();
        if (p == null)
            p = callback.createPresenter();
        if (p == null)
            throw new NullPointerException("callback.createPresenter() is null in MvpInternalDelegate");
        return p;
    }

    public void attachView() {
        callback.getPresenter().attachView(callback.getMvpView());
    }

    public void detachView() {
        callback.getPresenter().detachView();
    }
} 