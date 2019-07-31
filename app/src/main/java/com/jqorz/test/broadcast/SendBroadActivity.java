package com.jqorz.test.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.util.ToastUtil;

public class SendBroadActivity extends BaseActivity {
    public static final String INTENT_ACTION = "INTENT_ACTION";

    @Override
    protected void init() {
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_ACTION);
        registerReceiver(myReceiver, intentFilter);

        myReceiver.setOnReceiveBroadcastListener(new MyReceiver.OnReceiveBroadcastListener() {
            @Override
            public void onChanged(String data) {
                ToastUtil.showToast(mContext, data);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_broadcast;
    }

    public void sendBroad(View v) {
        Intent intent = new Intent();
        intent.setAction(INTENT_ACTION);
        intent.putExtra("data", "发送一个广播");
        sendBroadcast(intent);
    }
}
