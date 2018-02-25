package com.jqorz.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * @author jqorz
 * @since 2017/12/25
 * Toast工具类，可避免吐司长时间显示
 */
public class ToastUtil {
    /**
     * Toast方法
     *
     * @param text 需要展示的文本
     * @param context  所需上下文
     */

    private static Toast mToast = null;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(Context context) {
        String text = "点击";
        showToast(context, text);
    }
}
