package com.example.jqorz.mvptest.demo;

import com.example.jqorz.mvptest.lib.MvpView;
import com.example.jqorz.mvptest.lib.presenters.MvpPresenter;


public class MainContract {
    public interface IMainView extends MvpView {
        //将一些操作界面的方法在这里声明
        void method1();

        void method2();

    }

    public interface IMainPresenter extends MvpPresenter<IMainView> {
        //将一些逻辑处理的方法在此声明
        void init();

        void doSomeThing1();

        void doSomeThing2();
    }

    //这是Model的数据获取接口
    public interface onGetData {
        void onSuccess(String data);

        void onFail(String error);
    }
}
