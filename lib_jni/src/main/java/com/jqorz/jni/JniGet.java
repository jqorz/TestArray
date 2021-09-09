package com.jqorz.jni;

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

//    public static native int getResult();

    public int getNum1() {
        return 2;
    }

    public int getNum2() {
        return 2;
    }
}
