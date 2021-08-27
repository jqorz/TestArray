package com.jqorz.test.framework.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Button;

import com.jqorz.common.Logg;
import com.jqorz.common.base.BaseActivity;
import com.jqorz.test.R;
import com.jqorz.test.util.ToastUtil;

/**
 * @author jqorz
 * @since 2021/8/22
 */
public class ThreadTestActivity extends BaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadTestActivity.class);
        context.startActivity(starter);
    }
private Button mButton;
    @Override
    protected void init() {
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            new Thread(() -> {
                Logg.i("jqjq", "step 0 ");
                Looper.prepare();
                ToastUtil.showToast("run on Thread");
                Logg.i("jqjq", "step 1 ");
                Looper.loop();
                Logg.i("jqjq", "step 2 ");
            }).start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> {
            Looper.prepare();
            mButton.setText("onResume on Thread");
        }).start();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_thread_test;
    }
}
