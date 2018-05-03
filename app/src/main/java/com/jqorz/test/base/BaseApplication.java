package com.jqorz.test.base;

import android.app.Application;

import com.jqorz.test.BuglyTestActivity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by jqorz on 2017/12/16.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "5187dd5975", true);
    }
}
