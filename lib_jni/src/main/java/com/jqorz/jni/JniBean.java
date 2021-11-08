package com.jqorz.jni;

/**
 * @author jqorz
 * @since 2021/11/4
 */
class JniBean {
    String stuName;
    int age;

    public JniBean(String stuName, int age) {
        this.stuName = stuName;
        this.age = age;
    }

    public String getStuName() {
        return stuName;
    }

    public int getAge() {
        return age;
    }
}
