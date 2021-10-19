package com.example.jqorz.test1.main;

import com.example.jqorz.test1.mvp.presenters.BasePresenter;

/**
 * Created by jqorz on 2017/9/1.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter, MainContract.onGetData {
    private MainContract.IMainView view;
    private MainModel model=new MainModel();

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
    public void onSucc(String data) {
        //执行对界面的操作
        view.method1();
    }

    @Override
    public void onFail(String error) {
        //执行对界面的操作
        view.method2();
    }
}
