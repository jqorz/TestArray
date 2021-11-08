package com.jqorz.jni;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/5/6
 * <p>
 * https://www.cnblogs.com/wzben/p/5733571.html
 */
public class JniGet {
    static {
        System.loadLibrary("jni_test");
    }

    public static native String getHello();

    public static native void getListFromJava();

//    public static native int getResult();

    private static List<JniBean> getDataList(boolean sb) {
        ArrayList<JniBean> list = new ArrayList<>();
        list.add(new JniBean("jqjq1 " + sb, 21));
        list.add(new JniBean("jqjq2", 22));
        list.add(new JniBean("jqjq3", 23));
        return list;
    }

    //call from native
    public int getNum1() {
        return 2;
    }

    //call from native
    public int getNum2() {
        return 2;
    }
}
