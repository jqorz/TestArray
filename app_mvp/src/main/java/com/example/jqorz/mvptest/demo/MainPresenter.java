package com.example.jqorz.mvptest.demo;

import com.example.jqorz.mvptest.lib.presenters.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter, MainContract.onGetData {
    private MainContract.IMainView view;
    private final MainModel model = new MainModel();

    @Override
    public void init() {
        view = getMvpView();
        model.setListener(this);
    }

    @Override
    public void doSomeThing1() {

    }

    @Override
    public void doSomeThing2() {

    }

    @Override
    public void onSuccess(String data) {
        //执行对界面的操作
        view.method1();
    }

    @Override
    public void onFail(String error) {
        //执行对界面的操作
        view.method2();
    }
}
