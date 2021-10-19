package com.example.jqorz.test1.mvp.presenters;


import com.example.jqorz.test1.mvp.MvpView;

/**
 * Presenter的基础类，控制的MvpView的子类
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
