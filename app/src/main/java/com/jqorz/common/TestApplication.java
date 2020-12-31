package com.jqorz.common;

import android.app.Application;
import android.util.Log;

import com.jqorz.common.mac.DeviceUtils;
import com.jqorz.common.util.AppConfig;
import com.jqorz.common.util.ToolUtil;

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
