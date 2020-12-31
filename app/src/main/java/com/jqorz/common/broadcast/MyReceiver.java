package com.jqorz.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * 测试广播接收器用法
 */
public class MyReceiver extends BroadcastReceiver {
    private OnReceiveBroadcastListener onReceiveBroadcastListener;

    public void setOnReceiveBroadcastListener(OnReceiveBroadcastListener onReceiveBroadcastListener) {
        this.onReceiveBroadcastListener = onReceiveBroadcastListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), SendBroadActivity.INTENT_ACTION)) {
            if (onReceiveBroadcastListener != null) {
                onReceiveBroadcastListener.onChanged(intent.getStringExtra("data"));
            }
        }
    }

    public interface OnReceiveBroadcastListener {
        void onChanged(String text);
    }
}
