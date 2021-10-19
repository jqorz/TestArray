package com.example.jqorz.mvptest.lib.presenters;


import com.example.jqorz.mvptest.lib.MvpView;

/**
 * Presenter的基础类，控制的MvpView的子类
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
