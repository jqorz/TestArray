package com.example.jqorz.test1.main;

import com.example.jqorz.test1.BaseMvpActivity;
import com.example.jqorz.test1.R;

/**
 * 主界面的activity
 */
public class MainActivity extends BaseMvpActivity<MainPresenter, MainContract.IMainView> implements MainContract.IMainView {
    private MainPresenter presenter;

    @Override
    protected MainPresenter CreatePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void init() {
        //在这里进行控件ID的获取以及初始化
        presenter = getPresenter();
        presenter.init();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }


}
