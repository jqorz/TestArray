package com.jqorz.test;


public class MainEvent {

    public final static int GET_BASE_DATA = 0;//获取基础数据
    public final static int GET_SHOW_DATA = 1;//获取用于显示的数据
    public final static int LIST_REFRESH = 2;//列表刷新事件

    public final static int SEARCH = 3;//搜索事件

    private int arg1;
    private int arg2;
    private Object obj;



    public int getArg2() {
        return this.arg2;
    }

    public MainEvent setArg2(int arg2) {
        this.arg2 = arg2;
        return this;
    }

    public int getArg1() {
        return this.arg1;
    }

    public MainEvent setArg1(int arg1) {
        this.arg1 = arg1;
        return this;
    }

    public Object getObj() {
        return this.obj;
    }

    public MainEvent setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
