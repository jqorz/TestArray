package com.jqorz.test;

import android.app.Application;
import android.util.Log;

import com.jqorz.test.mac.DeviceUtils;
import com.jqorz.test.util.AppConfig;
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
        AppConfig.init(this);
        ToolUtil.init(this);

        String mac = DeviceUtils.getMacAddress();
        Log.e("mac1 ", mac);
    }
}