package com.example.jqorz.test1.mvp.delegate.fragment;


import com.example.jqorz.test1.mvp.MvpView;
import com.example.jqorz.test1.mvp.delegate.BaseDelegateCallback;
import com.example.jqorz.test1.mvp.presenters.MvpPresenter;

/**
 * Created by jqorz on 2017/8/28.
 * 生命周期回调
 */

public interface FragmentMvpDelegateCallback<P extends MvpPresenter, V extends MvpView>
        extends BaseDelegateCallback<P, V> {

}
