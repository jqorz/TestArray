package com.jqorz.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.LinkedList;

/**
 * APP配置信息
 */

public class AppConfig {
    private static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());
    private static int mScreenWidth;
    private static int mScreenHeight;
    private static String sUid;
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;
    private static long sStartTime;
    private static int sAliveActivity = 0;
    private static LinkedList<Activity> sActivityList = new LinkedList<>();
    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivity(activity);
            if (sAliveActivity++ == 0) sStartTime = System.currentTimeMillis();
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    private AppConfig() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param context context
     */
    public static void init(@NonNull final Context context) {
        AppConfig.sApplication = (Application) context.getApplicationContext();
        AppConfig.sApplication.registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            AppConfig.UTIL_HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        AppConfig.UTIL_HANDLER.postDelayed(runnable, delayMillis);
    }

    public static boolean isPortrait() {
        return getApp().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    static void setTopActivity(final Activity activity) {
        if (sActivityList.contains(activity)) {
            if (!sActivityList.getLast().equals(activity)) {
                sActivityList.remove(activity);
                sActivityList.addLast(activity);
            }
        } else {
            sActivityList.addLast(activity);
        }
    }

    public static LinkedList<Activity> getActivityList() {
        return sActivityList;
    }


    public static int getScreenWidth() {
        if (sApplication != null) {
            DisplayMetrics metrics = getApp().getResources().getDisplayMetrics();
            mScreenWidth = metrics.widthPixels;
        }
        return mScreenWidth;
    }

    public static int getScreenHeight() {
        if (sApplication != null) {
            DisplayMetrics metrics = getApp().getResources().getDisplayMetrics();
            mScreenHeight = metrics.heightPixels;
        }
        return mScreenHeight;
    }


    public static String getUid() {
        return TextUtils.isEmpty(sUid) ? "tea" : sUid;
    }

    public static void setUid(String sUid) {
        AppConfig.sUid = sUid;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getApp().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getApp().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
