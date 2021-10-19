package com.example.jqorz.mvptest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.delegate.fragment.FragmentDelegate;
import com.example.jqorz.mvptest.lib.delegate.fragment.FragmentDelegateImp;
import com.example.jqorz.mvptest.lib.delegate.fragment.FragmentMvpDelegateCallback;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;


/**
 * 使用MVP的Fragment
 */
public abstract class BaseMvpFragment<P extends MvpPresenter<V>, V extends MvpView> extends Fragment implements
        FragmentMvpDelegateCallback<P, V>, MvpView {
    private FragmentDelegate mFragmentDelegate;
    private P mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentDelegate = new FragmentDelegateImp<>(this);
        mFragmentDelegate.onCreateView();

        View view = inflater.inflate(getLayoutResId(), container, false);
        init();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mFragmentDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentDelegate.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFragmentDelegate.onStop();
    }

    protected abstract void init();

    protected abstract int getLayoutResId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentDelegate.onDestroyView();
    }

    @Override
    public void setPresenter() {

    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    @Override
    //这个方法由MvpInternalDelegate 调用BaseDelegateCallback 来创建presenter
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
}
