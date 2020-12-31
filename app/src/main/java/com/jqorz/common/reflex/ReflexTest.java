package com.jqorz.common.reflex;

import java.lang.reflect.Method;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/1/10
 */
class ReflexTest {

    public static void main(String[] args) {
        try {
            method1();
            method2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void method1() throws Exception {
        //没有类的实例对象
        Class<?> aClass = Class.forName("com.jqorz.common.reflex.MyTest");
        Method method = aClass.getDeclaredMethod("getStr");
        method.setAccessible(true);
        MyTest test = (MyTest) aClass.newInstance();
        String str = (String) method.invoke(test);
        System.out.println(str);
    }

    private static void method2() throws Exception {
        //已有类的实例对象
        MyTest myTest = new MyTest();
        Method method = myTest.getClass().getDeclaredMethod("getStr2", String.class);
        method.setAccessible(true);
        String str = (String) method.invoke(myTest, "awsl");
        System.out.println(str);
    }
}
