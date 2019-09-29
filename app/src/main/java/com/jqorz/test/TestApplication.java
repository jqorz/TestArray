package com.jqorz.test;

import android.app.Application;

import com.jqorz.test.util.ToolUtil;

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToolUtil.init(this);
    }
}
