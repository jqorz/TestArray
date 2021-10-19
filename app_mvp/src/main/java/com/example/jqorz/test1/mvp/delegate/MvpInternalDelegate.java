package com.example.jqorz.test1.mvp.delegate;


import com.example.jqorz.test1.mvp.MvpView;
import com.example.jqorz.test1.mvp.presenters.MvpPresenter;

/**
 * Created by jqorz on 2017/8/28.
 * 用于控制MvpPresenter与MvpView:
 */

public class MvpInternalDelegate<P extends MvpPresenter, V extends MvpView> {
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