package com.example.jqorz.mvptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.delegate.activity.ActivityDelegate;
import com.example.jqorz.mvptest.lib.delegate.activity.ActivityDelegateImp;
import com.example.jqorz.mvptest.lib.delegate.activity.ActivityMvpDelegateCallback;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;


/**
 * 基类Activity
 */
public abstract class BaseMvpActivity<P extends MvpPresenter<V>, V extends MvpView> extends AppCompatActivity implements
        ActivityMvpDelegateCallback<P, V>, MvpView {
    private ActivityDelegate mActivityDelegate;
    private P mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityDelegate = new ActivityDelegateImp<>(this);
        mActivityDelegate.onCreate();

        setContentView(getLayoutResId());


        init();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityDelegate.onStop();
    }


    @Override
    public void setPresenter() {

    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    @Override
    //这个方法由MvpInternalDelegate 调用 BaseDelegateCallback 来创建Presenter
    public P createPresenter() {
        mPresenter = CreatePresenter();
        return mPresenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityDelegate.onDestroy();

    }

    //初始化
    protected abstract void init();

    //用于获取布局id
    protected abstract int getLayoutResId();


}
