package com.example.jqorz.test1.mvp.delegate.activity;


import com.example.jqorz.test1.mvp.MvpView;
import com.example.jqorz.test1.mvp.delegate.BaseDelegateCallback;
import com.example.jqorz.test1.mvp.presenters.MvpPresenter;

/**
 * Created by jqorz on 2017/8/28.
 * 生命周期回调
 */

public interface ActivityMvpDelegateCallback<P extends MvpPresenter<V>, V extends MvpView>
        extends BaseDelegateCallback<P, V> {

}
