package com.jqorz.common.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
public class ToolUtil {
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    public static void init(Application application) {
        ToolUtil.sApplication = application;
    }

    public static int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sApplication.getResources().getDisplayMetrics()));
    }

    public static int getScreenWidth() {
        return sApplication.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return sApplication.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getFloatWindowType() {
        if (Build.VERSION.SDK_INT >= 26 && sApplication.getApplicationInfo().targetSdkVersion > 22) {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            return WindowManager.LayoutParams.TYPE_PHONE;//使用toast虽然不需要申请权限，但是会在部分机型上即使给予权限仍无法弹窗

        }
    }
}
