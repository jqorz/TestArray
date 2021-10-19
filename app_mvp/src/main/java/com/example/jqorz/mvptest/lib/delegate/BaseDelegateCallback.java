package com.example.jqorz.mvptest.lib.delegate;


import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;

/**
 * 用于对获取MvpView、创建以及获取Presenter
 */

public interface BaseDelegateCallback<P extends MvpPresenter<V>, V extends MvpView> {

    void setPresenter();

    P getPresenter();

    P createPresenter();

    V getMvpView();

}