package com.example.jqorz.test1.mvp.delegate.fragment;

/**
 * Created by jqorz on 2017/8/29.
 * 一个生命周期接口
 * 与Fragment生命周期一致，目的是为了通过Fragment的生命周期去控制是否要attachView：
 */

public interface FragmentDelegate {
    void onCreateView();

    void onPause();

    void onResume();

    void onStop();

    void onDestroyView();
}
