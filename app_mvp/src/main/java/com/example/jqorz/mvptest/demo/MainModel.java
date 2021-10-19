package com.example.jqorz.mvptest.demo;

/**
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
        listener.onSuccess(response);
        listener.onFail(error);
    }
}
