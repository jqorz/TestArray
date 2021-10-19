package com.example.jqorz.test1.mvp.delegate;


import com.example.jqorz.test1.mvp.MvpView;
import com.example.jqorz.test1.mvp.presenters.MvpPresenter;

/**
 * Created by jqorz on 2017/8/28.
 * 用于对获取MvpView、创建以及获取Presenter
 */

public interface BaseDelegateCallback<P extends MvpPresenter, V extends MvpView> {

    void setPresenter();

    P getPresenter();

    P createPresenter();

    V getMvpView();

}