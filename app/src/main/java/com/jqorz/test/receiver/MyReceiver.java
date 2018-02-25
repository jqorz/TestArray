package com.jqorz.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jqorz.test.ToastUtil;

/**
 * 测试广播接收器用法
 */
public class MyReceiver extends BroadcastReceiver {
    private OnListener onListener;

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("A")) {
            ToastUtil.showToast(context, "收到");
            if (onListener != null) {
                onListener.onChanged();
            }
        }
    }

    public interface OnListener {
        void onChanged();
    }
}
