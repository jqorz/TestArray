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
}
