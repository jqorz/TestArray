package com.example.jqorz.test1.main;

/**
 * Created by jqorz on 2017/9/2.
 * 主界面的Model类，用于数据的获取
 */

public class MainModel {
    private MainContract.onGetData listener;

    public void setListener(MainContract.onGetData listener) {
        this.listener = listener;
    }

    public void getData() {
        String response = "response";
        String error = "error";
        //执行数据获取的逻辑
        listener.onSucc(response);
        listener.onFail(error);
    }
}
