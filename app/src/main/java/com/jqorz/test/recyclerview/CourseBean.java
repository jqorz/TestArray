package com.jqorz.test.recyclerview;

/**
 * @author jqorz
 * @since 2018/7/20
 */
public class CourseBean {
    private String date;
    private int num;//标记当前数据在此组第几个

    public CourseBean(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;

    }

    public void setDate(String date) {
        this.date = date;
    }
}
